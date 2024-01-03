package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.model.community.Reply
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPostDetailRes(
    @SerialName("id")
    val id: Long,
    @SerialName("member")
    val member: String = "",
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("replies")
    val replies: List<GetPostDetailReplyRes>,
    @SerialName("reply_count")
    val replyCount: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String
) : DataMapper<PostDetail> {
    override fun toDomain(): PostDetail {
        return PostDetail(
            id = id,
            member = member,
            nickname = nickname,
            replies = replies.map { it.toDomain() },
            replyCount = replyCount,
            title = title,
            content = content
        )
    }
}

@Serializable
data class GetPostDetailReplyRes(
    @SerialName("id")
    val id: Long,
    @SerialName("member")
    val member: String = "",
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("reply")
    val reply: String
) : DataMapper<Reply> {
    override fun toDomain(): Reply {
        return Reply(
            id = id,
            member = member,
            nickname = nickname,
            reply = reply
        )
    }
}
