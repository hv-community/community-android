package com.hv.community.android.presentation.ui.community.post.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.presentation.model.community.post.detail.PostDetailFooterModel

class PostDetailFooterListAdapter(
    private val onNicknameChanged: (String) -> Unit,
    private val onPasswordChanged: (String) -> Unit,
    private val onContentChanged: (String) -> Unit,
    private val onWrite: () -> Unit
) : ListAdapter<PostDetailFooterModel, PostDetailFooterViewHolder>(PostDetailFooterListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailFooterViewHolder {
        return PostDetailFooterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PostDetailFooterViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onNicknameChanged = onNicknameChanged,
            onPasswordChanged = onPasswordChanged,
            onContentChanged = onContentChanged,
            onWrite = onWrite
        )
    }
}

private class PostDetailFooterListComparator : DiffUtil.ItemCallback<PostDetailFooterModel>() {
    override fun areItemsTheSame(oldItem: PostDetailFooterModel, newItem: PostDetailFooterModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostDetailFooterModel, newItem: PostDetailFooterModel): Boolean {
        return oldItem.id == newItem.id
                && oldItem.member == newItem.member
                && oldItem.isInit == newItem.isInit
    }
}
