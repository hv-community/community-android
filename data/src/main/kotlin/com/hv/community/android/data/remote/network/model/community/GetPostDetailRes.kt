package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.PostDetail
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPostDetailRes(
    @SerialName("id")
    val id: Long = -1L,
    @SerialName("title")
    val title: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("member_id")
    val memberId: Long = -1L,
    @SerialName("content")
    val content: String,
    @SerialName("previous_id")
    val previousId: Long = -1L,
    @SerialName("next_id")
    val nextId: Long = -1L,
    @SerialName("creation_time")
    val creationTime: Instant,
    @SerialName("modification_time")
    val modificationTime: Instant,
) : DataMapper<PostDetail> {
    override fun toDomain(): PostDetail {
        return PostDetail(
            id = id,
            title = title,
            nickname = nickname,
            memberId = memberId,
            content = content,
            previousId = previousId,
            nextId = nextId,
            creationTime = creationTime,
            modificationTime = modificationTime
        )
    }
}
