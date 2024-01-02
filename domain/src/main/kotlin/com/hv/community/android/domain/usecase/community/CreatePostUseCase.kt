package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        content: String,
        title: String,
        nickname: String,
        password: String
    ): Result<Unit> {
        return communityRepository.createPost(
            communityId = communityId,
            content = content,
            title = title,
            nickname = nickname,
            password = password
        )
    }
}
