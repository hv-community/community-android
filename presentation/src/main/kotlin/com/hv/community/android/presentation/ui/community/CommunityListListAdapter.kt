package com.hv.community.android.presentation.ui.community

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hv.community.android.domain.model.community.Community

class CommunityListListAdapter(
    private val onClick: (Community) -> Unit
) : ListAdapter<Community, CommunityListViewHolder>(CommunityListListComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityListViewHolder {
        return CommunityListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CommunityListViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            onClick = onClick
        )
    }
}

private class CommunityListListComparator : DiffUtil.ItemCallback<Community>() {
    override fun areItemsTheSame(oldItem: Community, newItem: Community): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Community, newItem: Community): Boolean {
        return oldItem == newItem
    }
}
