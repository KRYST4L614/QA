package com.example.randomuser.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomuser.databinding.FragmentMainBinding
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent
import com.example.randomuser.ui.adapters.UserPagingAdapter
import com.example.randomuser.ui.viewmodels.MainViewModel
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
        with(binding.userRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserPagingAdapter(callback!!)
            lifecycleScope.launch {
                viewModel.pagerLiveData.asFlow().collectLatest {
                    (adapter as UserPagingAdapter).submitData(it)
                }
            }
        }
        binding.refreshLayout.setOnRefreshListener {
            (binding.userRecyclerView.adapter as UserPagingAdapter).refresh()
            binding.refreshLayout.isRefreshing = false
        }
        return binding.root
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