package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class CheckPostPasswordUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        password: String,
        postId: Long
    ): Result<Unit> {
        return communityRepository.checkPostPassword(
            password = password,
            postId = postId
        )
    }
}
