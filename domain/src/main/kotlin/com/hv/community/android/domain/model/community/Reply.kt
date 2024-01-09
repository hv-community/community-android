package com.hv.community.android.domain.model.community

import java.time.Instant

data class Reply(
    val id: Long,
    val content: String,
    val nickname: String,
    val memberId: Long,
    val creationTime: Instant,
    val modificationTime: Instant,
)
