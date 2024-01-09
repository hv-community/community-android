package com.hv.community.android.presentation.ui.community.post.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.presentation.databinding.ItemPostDetailFooterBinding
import com.hv.community.android.presentation.model.community.post.detail.PostDetailFooterModel

class PostDetailFooterViewHolder(
    private val binding: ItemPostDetailFooterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: PostDetailFooterModel,
        onNicknameChanged: (String) -> Unit,
        onPasswordChanged: (String) -> Unit,
        onContentChanged: (String) -> Unit,
        onWrite: () -> Unit
    ) {
        with(binding) {
            nickname.setText(item.nickname)
            nickname.isEnabled = !item.isLogined && item.isInit
//        nickname.removeTextChangedListener() TODO
            nickname.doAfterTextChanged { onNicknameChanged(it.toString()) }

            dividerNickname.isVisible = !item.isLogined

            password.isVisible = !item.isLogined
            password.setText(item.password)
            password.isEnabled = item.isInit
//        password.removeTextChangedListener() TODO
            password.doAfterTextChanged { onPasswordChanged(it.toString()) }

            content.setText(item.content)
            content.isEnabled = item.isInit
//        content.removeTextChangedListener() TODO
            content.doAfterTextChanged { onContentChanged(it.toString()) }

            containerWrite.setOnClickListener {
                onWrite()
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostDetailFooterViewHolder {
            val binding = ItemPostDetailFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return PostDetailFooterViewHolder(binding)
        }
    }
}
