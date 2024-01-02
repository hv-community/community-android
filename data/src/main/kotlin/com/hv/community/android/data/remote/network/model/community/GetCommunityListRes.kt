package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.Community
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCommunityListRes(
    @SerialName("communities")
    val communities: List<GetCommunityListItemRes>
)

@Serializable
data class GetCommunityListItemRes(
    @SerialName("id")
    val id: Long,
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
