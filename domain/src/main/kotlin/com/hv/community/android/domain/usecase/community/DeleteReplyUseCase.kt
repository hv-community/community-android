package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class DeleteReplyUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long,
        replyId: Long,
        password: String
    ): Result<Unit> {
        return communityRepository.deleteReply(
            communityId = communityId,
            postId = postId,
            replyId = replyId,
            password = password
        )
    }
}
