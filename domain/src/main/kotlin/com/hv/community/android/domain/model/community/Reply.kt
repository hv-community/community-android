package com.hv.community.android.domain.model.community

import kotlinx.datetime.Instant

data class Reply(
    val id: Long,
    val content: String,
    val nickname: String,
    val memberId: Long,
    val creationTime: Instant,
    val modificationTime: Instant,
)
