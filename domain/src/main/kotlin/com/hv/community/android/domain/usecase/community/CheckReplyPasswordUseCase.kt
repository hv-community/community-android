package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class CheckReplyPasswordUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        password: String,
        replyId: Long
    ): Result<Unit> {
        return communityRepository.checkReplyPassword(
            password = password,
            replyId = replyId
        )
    }
}
