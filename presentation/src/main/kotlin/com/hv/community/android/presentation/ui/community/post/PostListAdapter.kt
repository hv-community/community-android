package com.hv.community.android.presentation.ui.community.post

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.domain.model.community.Post

class PostListAdapter(
    private val onClick: (Post) -> Unit
) : ListAdapter<Post, PostViewHolder>(PostListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onClick = onClick
        )
    }
}

private class PostListComparator : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
