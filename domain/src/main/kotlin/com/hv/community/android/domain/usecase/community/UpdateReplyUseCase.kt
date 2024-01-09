package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class UpdateReplyUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long,
        replyId: Long,
        password: String,
        content: String
    ): Result<Unit> {
        return communityRepository.updateReply(
            communityId = communityId,
            postId = postId,
            replyId = replyId,
            password = password,
            content = content,
        )
    }
}
