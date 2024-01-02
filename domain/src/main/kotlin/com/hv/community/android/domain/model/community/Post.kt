package com.hv.community.android.domain.model.community

data class Post(
    val id: Long,
    val title: String,
    val member: String,
    val nickname: String,
    val replyCount: Int
)
