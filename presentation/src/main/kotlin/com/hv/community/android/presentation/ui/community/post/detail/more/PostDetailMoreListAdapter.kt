package com.hv.community.android.presentation.ui.community.post.detail.more

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.presentation.model.community.post.detail.more.PostDetailMoreAction

class PostDetailMoreListAdapter(
    private val onClick: (PostDetailMoreAction) -> Unit
) : ListAdapter<PostDetailMoreAction, PostDetailMoreViewHolder>(PostDetailMoreListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailMoreViewHolder {
        return PostDetailMoreViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PostDetailMoreViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onClick = onClick
        )
    }
}

private class PostDetailMoreListComparator : DiffUtil.ItemCallback<PostDetailMoreAction>() {
    override fun areItemsTheSame(oldItem: PostDetailMoreAction, newItem: PostDetailMoreAction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostDetailMoreAction, newItem: PostDetailMoreAction): Boolean {
        return oldItem == newItem
    }
}
