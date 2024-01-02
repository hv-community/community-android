package com.hv.community.android.presentation.ui.community

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.domain.model.community.Community

class CommunityListAdapter(
    private val onClick: (Community) -> Unit,
    private val onLongClick: (Community) -> Unit
) : ListAdapter<Community, CommunityViewHolder>(CommunityListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        return CommunityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onClick = onClick,
            onLongClick = onLongClick
        )
    }
}

private class CommunityListComparator : DiffUtil.ItemCallback<Community>() {
    override fun areItemsTheSame(oldItem: Community, newItem: Community): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Community, newItem: Community): Boolean {
        return oldItem == newItem
    }
}
