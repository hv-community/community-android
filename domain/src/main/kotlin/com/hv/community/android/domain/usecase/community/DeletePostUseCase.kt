package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        password: String,
        postId: Long
    ): Result<Unit> {
        return communityRepository.deletePost(
            password = password,
            postId = postId
        )
    }
}
