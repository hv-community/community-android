package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long
    ): Result<PostDetail> {
        return communityRepository.getPostDetail(
            communityId = communityId,
            postId = postId
        )
    }
}
