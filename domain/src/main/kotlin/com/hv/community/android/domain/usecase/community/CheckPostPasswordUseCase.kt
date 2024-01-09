package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class CheckPostPasswordUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long,
        password: String
    ): Result<Unit> {
        return communityRepository.checkPostPassword(
            postId = postId,
            communityId = communityId,
            password = password
        )
    }
}
