package com.hv.community.android.data.remote.network.model.community

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.domain.model.community.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPostListRes(
    @SerialName("posts")
    val posts: List<GetPostListItemRes>
)

@Serializable
data class GetPostListItemRes(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("member")
    val member: String = "",
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("reply_count")
    val replyCount: Int
) : DataMapper<Post> {
    override fun toDomain(): Post {
        return Post(
            id = id,
            title = title,
            member = member,
            nickname = nickname,
            replyCount = replyCount
        )
    }
}
