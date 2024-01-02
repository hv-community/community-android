package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        content: String,
        password: String,
        postId: Long,
        title: String
    ): Result<Unit> {
        return communityRepository.updatePost(
            content = content,
            password = password,
            postId = postId,
            title = title
        )
    }
}
