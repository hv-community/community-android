package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.Community
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCommunityListRes(
    @SerialName("next")
    val next: Int = -1,
    @SerialName("prev")
    val prev: Int = -1,
    @SerialName("total_page")
    val totalPage: Int,
    @SerialName("page")
    val page: Int,
    @SerialName("page_size")
    val pageSize: Int,
    @SerialName("items")
    val items: List<GetCommunityListItemRes>,
)

@Serializable
data class GetCommunityListItemRes(
    @SerialName("id")
    val id: Long = -1L,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("thumbnail")
    val thumbnail: String
) : DataMapper<Community> {
    override fun toDomain(): Community {
        return Community(
            id = id,
            title = title,
            description = description,
            thumbnail = thumbnail
        )
    }
}
