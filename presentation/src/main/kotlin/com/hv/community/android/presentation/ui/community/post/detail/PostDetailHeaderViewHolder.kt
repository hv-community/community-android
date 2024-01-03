package com.hv.community.android.presentation.ui.community.post.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.presentation.databinding.ItemPostDetailHeaderBinding
import com.hv.community.android.presentation.model.community.post.detail.PostDetailHeaderModel

class PostDetailHeaderViewHolder(
    private val binding: ItemPostDetailHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: PostDetailHeaderModel
    ) {
        with(binding) {
            title.text = item.title
            nickname.text = item.nickname
            content.isEnabled = item.isInit
            content.text = item.content
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostDetailHeaderViewHolder {
            val binding = ItemPostDetailHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return PostDetailHeaderViewHolder(binding)
        }
    }
}
