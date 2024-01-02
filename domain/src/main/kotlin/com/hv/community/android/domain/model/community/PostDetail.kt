package com.hv.community.android.domain.model.community

data class PostDetail(
    val id: Long,
    val member: String,
    val nickname: String,
    val replies: List<Reply>,
    val replyCount: Int,
    val title: String
)

