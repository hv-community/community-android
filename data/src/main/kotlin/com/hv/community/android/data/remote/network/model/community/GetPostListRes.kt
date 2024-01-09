package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class GetPostListRes(
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
    val items: List<GetPostListItemRes>
)

@Serializable
data class GetPostListItemRes(
    @SerialName("id")
    val id: Long = -1L,
    @SerialName("title")
    val title: String,
    @SerialName("reply_count")
    val replyCount: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("creation_time")
    val creationTime: Instant,
    @SerialName("modification_time")
    val modificationTime: Instant
) : DataMapper<Post> {
    override fun toDomain(): Post {
        return Post(
            id = id,
            title = title,
            replyCount = replyCount,
            nickname = nickname,
            creationTime = creationTime,
            modificationTime = modificationTime
        )
    }
}
