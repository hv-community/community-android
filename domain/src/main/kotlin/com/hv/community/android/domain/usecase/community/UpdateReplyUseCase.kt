package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class UpdateReplyUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        password: String,
        reply: String,
        replyId: Long
    ): Result<Unit> {
        return communityRepository.updateReply(
            password = password,
            reply = reply,
            replyId = replyId
        )
    }
}
