package com.example.randomuser.ui.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.randomuser.data.database.models.UserEntity

abstract class UserHolder(
    view: View
) : ViewHolder(view) {
    abstract fun bind(userEntity: UserEntity)
}