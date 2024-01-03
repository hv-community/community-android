package com.hv.community.android.domain.model.community

data class PostDetail(
    val id: Long = 0L,
    val member: String = "",
    val nickname: String = "",
    val replies: List<Reply> = emptyList(),
    val replyCount: Int = 0,
    val title: String = "",
    val content: String = ""
)

