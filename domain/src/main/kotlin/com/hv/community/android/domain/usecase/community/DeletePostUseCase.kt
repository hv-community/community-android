package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long,
        password: String
    ): Result<Unit> {
        return communityRepository.deletePost(
            communityId = communityId,
            postId = postId,
            password = password
        )
    }
}
