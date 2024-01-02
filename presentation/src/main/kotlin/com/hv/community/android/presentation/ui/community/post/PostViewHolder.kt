package com.hv.community.android.presentation.ui.community.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.presentation.databinding.ItemPostBinding

class PostViewHolder(
    private val binding: ItemPostBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Post,
        onClick: (Post) -> Unit
    ) {
        with(binding) {
            title.text = item.title
            nickname.text = "작성자 : ${item.member.ifEmpty { item.nickname }}"
            textComment.text = item.replyCount.toString()
            container.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostViewHolder {
            val binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return PostViewHolder(binding)
        }
    }
}
