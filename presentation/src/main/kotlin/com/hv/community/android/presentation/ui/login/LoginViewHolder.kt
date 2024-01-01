package com.hv.community.android.presentation.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.presentation.databinding.ItemLoginBinding
import com.hv.community.android.presentation.model.login.LoginMethod

class LoginViewHolder(
    private val binding: ItemLoginBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: LoginMethod,
        onClick: (LoginMethod) -> Unit
    ) {
        with(binding) {
            icon.setImageResource(item.iconRes)
            title.text = item.title
            container.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): LoginViewHolder {
            val binding = ItemLoginBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return LoginViewHolder(binding)
        }
    }
}
