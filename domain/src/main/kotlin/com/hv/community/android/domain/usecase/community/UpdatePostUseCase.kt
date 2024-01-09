package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long,
        title: String,
        content: String,
        password: String,
    ): Result<Unit> {
        return communityRepository.updatePost(
            communityId = communityId,
            postId = postId,
            title = title,
            content = content,
            password = password
        )
    }
}
