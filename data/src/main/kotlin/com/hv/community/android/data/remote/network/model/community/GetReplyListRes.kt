package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.Reply
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class GetReplyListRes(
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
    val items: List<GetReplyListItemRes>
)

@Serializable
data class GetReplyListItemRes(
    @SerialName("id")
    val id: Long = -1L,
    @SerialName("content")
    val content: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("member_id")
    val memberId: Long = -1L,
    @SerialName("creation_time")
    val creationTime: Instant,
    @SerialName("modification_time")
    val modificationTime: Instant,
) : DataMapper<Reply> {
    override fun toDomain(): Reply {
        return Reply(
            id = id,
            content = content,
            nickname = nickname,
            memberId = memberId,
            creationTime = creationTime,
            modificationTime = modificationTime
        )
    }
}
