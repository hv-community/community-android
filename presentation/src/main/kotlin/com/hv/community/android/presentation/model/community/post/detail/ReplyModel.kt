package com.hv.community.android.presentation.model.community.post.detail

import android.os.Parcelable
import com.hv.community.android.domain.model.community.Reply
import kotlinx.android.parcel.Parcelize

// TODO : sealed 로 분리
@Parcelize
data class ReplyModel(
    val id: Long,
    val member: String,
    val nickname: String,
    val reply: String,
    val isExpanded: Boolean = false,
    val password: String = "",
    val fixedContent: String = reply,
    val isEditing: Boolean = false,
    val isDeleting: Boolean = false,
    val isExpandEnabled: Boolean
) : Parcelable

fun Reply.toUiModel(
    isExpandEnabled: Boolean
): ReplyModel {
    return ReplyModel(
        id = id,
        member = member,
        nickname = nickname,
        reply = reply,
        isExpandEnabled = isExpandEnabled
    )
}
