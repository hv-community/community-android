package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class CreateReplyUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long,
        nickname: String,
        password: String,
        content: String
    ): Result<Long> {
        return communityRepository.createReply(
            communityId = communityId,
            postId = postId,
            content = content,
            nickname = nickname,
            password = password
        )
    }
}
