package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.model.community.Reply
import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class GetReplyListUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        communityId: Long,
        postId: Long
    ): Result<List<Reply>> {
        return communityRepository.getReplyList(
            communityId = communityId,
            postId = postId
        )
    }
}
