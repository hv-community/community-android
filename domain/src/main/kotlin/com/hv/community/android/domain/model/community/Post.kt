package com.hv.community.android.domain.model.community

import kotlinx.datetime.Instant

data class Post(
    val id: Long,
    val title: String,
    val replyCount: Int,
    val nickname: String,
    val creationTime: Instant,
    val modificationTime: Instant
)
