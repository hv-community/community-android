package com.hv.community.android.presentation.ui.login

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.presentation.model.login.LoginMethod

class LoginListAdapter(
    private val onClick: (LoginMethod) -> Unit
) : ListAdapter<LoginMethod, LoginViewHolder>(LoginListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginViewHolder {
        return LoginViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onClick = onClick
        )
    }
}

private class LoginListComparator : DiffUtil.ItemCallback<LoginMethod>() {
    override fun areItemsTheSame(oldItem: LoginMethod, newItem: LoginMethod): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LoginMethod, newItem: LoginMethod): Boolean {
        return oldItem == newItem
    }
}
