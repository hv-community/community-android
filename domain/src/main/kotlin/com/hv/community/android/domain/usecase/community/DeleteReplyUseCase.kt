package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class DeleteReplyUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        password: String,
        replyId: Long
    ): Result<Unit> {
        return communityRepository.deleteReply(
            password = password,
            replyId = replyId
        )
    }
}
