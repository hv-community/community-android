package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class CreateReplyUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        nickname: String,
        password: String,
        postId: Long,
        reply: String
    ): Result<Unit> {
        return communityRepository.createReply(
            nickname = nickname,
            password = password,
            postId = postId,
            reply = reply
        )
    }
}
