package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long
    ): Result<List<Post>> {
        return communityRepository.getPostList(
            communityId = communityId
        )
    }
}
