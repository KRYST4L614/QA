package com.example.randomuser.ui.fragments

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuser.R
import com.example.randomuser.appComponent
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.databinding.UserInfoFragmentBinding
import com.example.randomuser.ui.viewmodels.MainViewModel
import com.example.randomuser.utils.Utils
import com.squareup.picasso.Picasso
import javax.inject.Inject

private const val USER_ENTITY_KEY = "userEntity"

class UserInfoFragment : Fragment() {
    @Inject
    lateinit var factory: MainViewModel.Factory
    private var _binding: UserInfoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserInfoFragmentBinding.inflate(layoutInflater)
        requireActivity().appComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userEntity: UserEntity = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(USER_ENTITY_KEY, UserEntity::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(USER_ENTITY_KEY)!!
        }
        with(binding) {
            Picasso.get().load(userEntity.pictureLarge)
                .placeholder(R.drawable.account_placeholder).into(avatarInfo.avatar)
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
            personalInfo.registeredDate.text =
                requireContext().getString(R.string.registration_date)
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
            userEntity.latitude?.let {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(userEntity: UserEntity): UserInfoFragment {
            val args = Bundle().apply {
                putParcelable(USER_ENTITY_KEY, userEntity)
            }
            return UserInfoFragment().apply {
                arguments = args
            }
        }
    }
}