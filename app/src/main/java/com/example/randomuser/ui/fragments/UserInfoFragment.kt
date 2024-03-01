package com.example.randomuser.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randomuser.R
import com.example.randomuser.databinding.UserInfoFragmentBinding
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent
import com.example.randomuser.ui.viewmodels.MainViewModel
import com.example.randomuser.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import javax.inject.Inject

private const val USER_ID_KEY = "userId"

class UserInfoFragment : Fragment() {
    @Inject
    lateinit var factory: MainViewModel.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
    }
    private lateinit var appComponent: AppComponent
    private var _binding: UserInfoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserInfoFragmentBinding.inflate(layoutInflater)
        appComponent = DaggerAppComponent.builder().context(requireContext()).build()
        appComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.avatarInfo.avatarError.avatarProgressBar.isVisible = true
        binding.avatarInfo.avatar.isVisible = false
        binding.avatarInfo.avatarError.errorMessage.isVisible = false
        binding.avatarInfo.avatarError.retryButton.isVisible = false
        viewModel.getUserById(arguments?.getInt(USER_ID_KEY)!!).observe(
            viewLifecycleOwner
        ) { userEntity ->
            with(binding) {
                Picasso.get().load(userEntity.pictureLarge).into(avatarInfo.avatar, object :
                    Callback {
                    override fun onSuccess() {
                        binding.avatarInfo.avatarError.avatarProgressBar.isVisible = false
                        binding.avatarInfo.avatar.isVisible = true
                    }

                    override fun onError(e: Exception?) {
                        binding.avatarInfo.avatarError.avatarProgressBar.isVisible = false
                        binding.avatarInfo.avatarError.errorMessage.isVisible = true
                        binding.avatarInfo.avatarError.retryButton.isVisible = true
                    }

                })
                avatarInfo.name.text =
                    listOf(
                        userEntity.titleName,
                        userEntity.firstName,
                        userEntity.lastName
                    ).joinToString(" ")
                userEntity.phone?.let { phone ->
                    avatarInfo.callButton.setOnClickListener {
                        Utils.sendPhoneCallIntent(requireContext(), phone)
                    }
                    contacts.phoneNumber.setOnClickListener {
                        Utils.sendPhoneCallIntent(requireContext(), phone)
                    }
                    userEntity.cell?.let { cell ->
                        contacts.cellPhoneNumber.setOnClickListener {
                            Utils.sendPhoneCallIntent(requireContext(), cell)
                        }
                    }
                }
                userEntity.email?.let { email ->
                    avatarInfo.messageButton.setOnClickListener {
                        Utils.sendEmailIntent(requireContext(), email)
                    }
                }
                personalInfo.city.text =
                    requireContext().getString(R.string.city).format(
                        requireContext().getString(R.string.city_placeholder)
                            .format(userEntity.city, userEntity.country)
                    )
                personalInfo.birthdate.text =
                    requireContext().getString(R.string.birthdate)
                        .format(DateFormat.format("d MMMM yyyy", userEntity.birthdate))
                personalInfo.username.text =
                    requireContext().getString(R.string.username).format(userEntity.username)
                personalInfo.registeredDate.text = requireContext().getString(R.string.birthdate)
                    .format(DateFormat.format("d MMMM yyyy", userEntity.registerDate))
                contacts.email.text =
                    requireContext().getString(R.string.email).format(userEntity.email)
                contacts.phoneNumber.text =
                    requireContext().getString(R.string.phone_number).format(userEntity.phone)
                contacts.cellPhoneNumber.text =
                    requireContext().getString(R.string.cell_phone_number)
                        .format(userEntity.cell)
                personalInfo.timezone.text =
                    requireContext().getString(R.string.timezone).format(userEntity.timezoneOffset)
                contacts.postcode.text =
                    requireContext().getString(R.string.postcode).format(userEntity.postcode)
                userEntity?.latitude?.let {
                    userEntity.longitude?.let {
                        personalInfo.city.setOnClickListener {
                            Utils.sendLocationIntent(
                                requireContext(),
                                userEntity.latitude,
                                userEntity.longitude
                            )
                        }
                        avatarInfo.locationButton.setOnClickListener {
                            Utils.sendLocationIntent(
                                requireContext(),
                                userEntity.latitude,
                                userEntity.longitude
                            )
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(userId: Int): UserInfoFragment {
            val args = Bundle().apply {
                putInt(USER_ID_KEY, userId)
            }
            return UserInfoFragment().apply {
                arguments = args
            }
        }
    }
}