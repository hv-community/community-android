package com.hv.community.android.presentation.ui.community.post.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.presentation.model.community.post.detail.PostDetailHeaderModel

class PostDetailHeaderListAdapter(

) : ListAdapter<PostDetailHeaderModel, PostDetailHeaderViewHolder>(PostDetailHeaderListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailHeaderViewHolder {
        return PostDetailHeaderViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PostDetailHeaderViewHolder, position: Int) {
        holder.bind(
            item = getItem(position)
        )
    }
}

private class PostDetailHeaderListComparator : DiffUtil.ItemCallback<PostDetailHeaderModel>() {
    override fun areItemsTheSame(oldItem: PostDetailHeaderModel, newItem: PostDetailHeaderModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostDetailHeaderModel, newItem: PostDetailHeaderModel): Boolean {
        return oldItem == newItem
    }
}
