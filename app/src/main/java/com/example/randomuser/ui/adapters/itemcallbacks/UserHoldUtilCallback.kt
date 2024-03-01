package com.example.randomuser.ui.adapters.itemcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.randomuser.data.database.models.UserEntity

class UserHoldUtilCallback() : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.primaryId == newItem.primaryId
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}