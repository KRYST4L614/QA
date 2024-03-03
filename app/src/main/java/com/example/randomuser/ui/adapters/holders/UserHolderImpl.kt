package com.example.randomuser.ui.adapters.holders

import android.net.Uri
import android.view.View
import android.view.View.OnClickListener
import com.example.randomuser.R
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.databinding.UserItemBinding
import com.example.randomuser.ui.fragments.MainFragment
import com.example.randomuser.utils.Utils
import com.squareup.picasso.Picasso

class UserHolderImpl(
    private val binding: UserItemBinding,
    private val callback: MainFragment.Callback
) : UserHolder(binding.root), OnClickListener {
    private lateinit var userEntity: UserEntity
    override fun bind(userEntity: UserEntity) {
        this.userEntity = userEntity
        with(userEntity) {
            binding.name.text = listOf(
                titleName ?: "",
                firstName ?: "",
                lastName ?: ""
            ).joinToString(" ")
        }
        userEntity.city?.let { city ->
            binding.info.text = binding.root.context.getString(R.string.city_placeholder)
                .format(city, userEntity.country)
        }
        userEntity.pictureLarge.let {
            Picasso.get().load(Uri.parse(it)).placeholder(R.drawable.account_circle).into(binding.avatar)
        }
        userEntity.phone?.let { phone ->
            binding.callButton.setOnClickListener {
                Utils.sendPhoneCallIntent(binding.root.context, phone)
            }
        }
        userEntity.email?.let { email ->
            binding.messageButton.setOnClickListener {
                Utils.sendEmailIntent(binding.root.context, email)
            }
        }
        binding.root.setOnClickListener(
            this
        )
    }

    override fun onClick(v: View?) {
        callback.userSelected(userId = userEntity.primaryId)
    }

}