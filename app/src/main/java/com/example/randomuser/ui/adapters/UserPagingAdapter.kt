package com.example.randomuser.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.randomuser.ui.adapters.holders.UserHolder
import com.example.randomuser.ui.adapters.holders.UserHolderImpl
import com.example.randomuser.ui.adapters.itemcallbacks.UserHoldUtilCallback
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.databinding.UserItemBinding
import com.example.randomuser.ui.fragments.MainFragment

class UserPagingAdapter(
    private val callback: MainFragment.Callback
) : PagingDataAdapter<UserEntity, UserHolder>(UserHoldUtilCallback()) {
    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolderImpl(binding, callback)
    }
}