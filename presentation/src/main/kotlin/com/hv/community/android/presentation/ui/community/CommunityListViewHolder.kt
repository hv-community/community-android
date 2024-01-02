package com.hv.community.android.presentation.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.presentation.databinding.ItemCommunityListBinding
import com.ray.rds.util.bindingadapter.setImageUrl

class CommunityListViewHolder(
    private val binding: ItemCommunityListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Community,
        onClick: (Community) -> Unit
    ) {
        with(binding) {
            title.text = item.title
            icon.setImageUrl(item.thumbnail)
            root.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CommunityListViewHolder {
            val binding = ItemCommunityListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return CommunityListViewHolder(binding)
        }
    }
}
