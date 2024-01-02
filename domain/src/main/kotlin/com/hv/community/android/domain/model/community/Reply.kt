package com.hv.community.android.domain.model.community

data class Reply(
    val id: Long,
    val member: String,
    val nickname: String,
    val reply: String
)
