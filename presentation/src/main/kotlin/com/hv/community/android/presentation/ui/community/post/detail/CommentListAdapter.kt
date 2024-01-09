package com.hv.community.android.presentation.ui.community.post.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.presentation.model.community.post.detail.ReplyModel

class CommentListAdapter(
    private val onPasswordChanged: (Long, String) -> Unit,
    private val onContentChanged: (Long, String) -> Unit,
    private val onExpand: (Long, Boolean) -> Unit,
    private val onEditing: (Long, Boolean) -> Unit,
    private val onDeleting: (Long, Boolean) -> Unit,
    private val onEditClick: (ReplyModel) -> Unit,
    private val onDeleteClick: (ReplyModel) -> Unit
) : ListAdapter<ReplyModel, CommentViewHolder>(PostDetailCommentListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onPasswordChanged = onPasswordChanged,
            onContentChanged = onContentChanged,
            onExpand = onExpand,
            onEditing = onEditing,
            onDeleting = onDeleting,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick
        )
    }
}

private class PostDetailCommentListComparator : DiffUtil.ItemCallback<ReplyModel>() {
    override fun areItemsTheSame(oldItem: ReplyModel, newItem: ReplyModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ReplyModel, newItem: ReplyModel): Boolean {
        return oldItem.id == newItem.id
                && oldItem.content == newItem.content
                && oldItem.nickname == newItem.nickname
                && oldItem.memberId == newItem.memberId
//                && oldItem.creationTime == newItem.creationTime
//                && oldItem.modificationTime == newItem.modificationTime
    }
}
