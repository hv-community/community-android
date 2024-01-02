package com.hv.community.android.presentation.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.presentation.databinding.ItemCommunityBinding
import com.ray.rds.util.bindingadapter.setImageUrl

class CommunityViewHolder(
    private val binding: ItemCommunityBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Community,
        onClick: (Community) -> Unit,
        onLongClick: (Community) -> Unit
    ) {
        with(binding) {
            title.text = item.title
            icon.setImageUrl(item.thumbnail)
            root.setOnClickListener {
                onClick(item)
            }
            root.setOnLongClickListener {
                onLongClick(item)
                true
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CommunityViewHolder {
            val binding = ItemCommunityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return CommunityViewHolder(binding)
        }
    }
}
