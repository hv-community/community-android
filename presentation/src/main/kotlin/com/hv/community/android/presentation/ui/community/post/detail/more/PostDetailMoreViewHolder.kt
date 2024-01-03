package com.hv.community.android.presentation.ui.community.post.detail.more

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.presentation.databinding.ItemPostDetailMoreBinding
import com.hv.community.android.presentation.model.community.post.detail.more.PostDetailMoreAction

class PostDetailMoreViewHolder(
    private val binding: ItemPostDetailMoreBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: PostDetailMoreAction,
        onClick: (PostDetailMoreAction) -> Unit
    ) {
        with(binding) {
            title.text = item.title
            container.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostDetailMoreViewHolder {
            val binding = ItemPostDetailMoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return PostDetailMoreViewHolder(binding)
        }
    }
}
