package com.hv.community.android.domain.model.community

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class PostDetail(
    val id: Long = -1L,
    val title: String = "",
    val nickname: String = "",
    val memberId: Long = -1L,
    val content: String = "",
    val previousId: Long = -1L,
    val nextId: Long = -1L,
    val creationTime: Instant = Clock.System.now(),
    val modificationTime: Instant = Clock.System.now(),
)

