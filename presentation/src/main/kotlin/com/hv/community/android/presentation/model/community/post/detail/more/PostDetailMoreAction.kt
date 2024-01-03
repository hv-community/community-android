package com.hv.community.android.presentation.model.community.post.detail.more

sealed class PostDetailMoreAction(
    val title: String
) {
    data object Edit : PostDetailMoreAction(
        title = "수정"
    )

    data object Delete : PostDetailMoreAction(
        title = "삭제"
    )
}
