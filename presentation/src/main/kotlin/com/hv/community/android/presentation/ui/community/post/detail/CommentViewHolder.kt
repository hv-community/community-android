package com.hv.community.android.presentation.ui.community.post.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.presentation.common.view.OnStateChangedListener
import com.hv.community.android.presentation.common.view.OnTextChangedListener
import com.hv.community.android.presentation.databinding.ItemCommentBinding
import com.hv.community.android.presentation.model.community.post.detail.ReplyModel

class CommentViewHolder(
    private val binding: ItemCommentBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: ReplyModel,
        onPasswordChanged: (Long, String) -> Unit,
        onContentChanged: (Long, String) -> Unit,
        onExpand: (Long, Boolean) -> Unit,
        onEditing: (Long, Boolean) -> Unit,
        onDeleting: (Long, Boolean) -> Unit,
        onEditClick: (ReplyModel) -> Unit,
        onDeleteClick: (ReplyModel) -> Unit
    ) {
        with(binding.comment) {
            content = item.reply
            nickname = item.member.ifEmpty { item.nickname }
            isPasswordVisible = item.member.isEmpty()
            this.onPasswordChanged = OnTextChangedListener { onPasswordChanged(item.id, it) }
            this.onContentChanged = OnTextChangedListener { onContentChanged(item.id, it) }
            this.onExpand = OnStateChangedListener { onExpand(item.id, it) }
            this.onEditing = OnStateChangedListener { onEditing(item.id, it) }
            this.onDeleting = OnStateChangedListener { onDeleting(item.id, it) }
            this.onEditClick = View.OnClickListener { onEditClick(item) }
            this.onDeleteClick = View.OnClickListener { onDeleteClick(item) }
            isExpanded = item.isExpanded
            password = item.password
            fixedContent = item.fixedContent
            isEditing = item.isEditing
            isExpandEnabled = item.isExpandEnabled
        }
    }

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder {
            val binding = ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return CommentViewHolder(binding)
        }
    }
}
