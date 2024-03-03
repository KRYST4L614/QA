package com.example.randomuser.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentMainBinding
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent
import com.example.randomuser.ui.adapters.UserPagingAdapter
import com.example.randomuser.ui.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var factory: MainViewModel.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }
    private lateinit var appComponent: AppComponent
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var callback: Callback? = null
    private val adapter: UserPagingAdapter by lazy {
        UserPagingAdapter(callback!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        appComponent = DaggerAppComponent
            .builder()
            .context(requireContext())
            .build()
        appComponent.inject(this)
        with(binding) {
            userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            userRecyclerView.adapter = adapter
            lifecycleScope.launch {
                viewModel.pagerLiveData.asFlow().collectLatest {
                    adapter.submitData(it)
                }
            }
        }
        binding.refreshLayout.setOnRefreshListener {
            adapter.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.addLoadStateListener { state ->
            with(binding) {
                val refreshState = state.refresh
                userRecyclerView.isVisible = refreshState !is LoadState.Loading
                progress.isVisible = refreshState is LoadState.Loading
                cloudOff.isVisible = adapter.itemCount == 0 && refreshState is LoadState.Error
                errorMessage.isVisible = cloudOff.isVisible
                if (refreshState is LoadState.Error) {
                    errorMessage.text = refreshState.error.localizedMessage
                    Snackbar.make(
                        binding.root,
                        refreshState.error.localizedMessage
                            ?: requireContext().getText(R.string.network_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callback?
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    interface Callback {
        fun userSelected(userId: Int)
    }
}