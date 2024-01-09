package com.hv.community.android.presentation.model.community.post.detail

import android.os.Parcelable
import com.hv.community.android.domain.model.community.Reply
import kotlinx.android.parcel.Parcelize
import java.time.Instant

// TODO : sealed 로 분리
@Parcelize
data class ReplyModel(
    val id: Long,
    val content: String,
    val nickname: String,
    val memberId: Long,
    val creationTime: Instant,
    val modificationTime: Instant,
    val isExpanded: Boolean = false,
    val password: String = "",
    val fixedContent: String = content,
    val isEditing: Boolean = false,
    val isDeleting: Boolean = false,
    val isExpandEnabled: Boolean
) : Parcelable

fun Reply.toUiModel(
    isExpandEnabled: Boolean
): ReplyModel {
    return ReplyModel(
        id = id,
        content = content,
        nickname = nickname,
        memberId = memberId,
        creationTime = creationTime,
        modificationTime = modificationTime,
        isExpandEnabled = isExpandEnabled
    )
}
