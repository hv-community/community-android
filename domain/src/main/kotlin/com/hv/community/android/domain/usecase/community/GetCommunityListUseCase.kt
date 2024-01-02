package com.hv.community.android.domain.usecase.community

import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.repository.CommunityRepository
import javax.inject.Inject

class GetCommunityListUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(): Result<List<Community>> {
        return communityRepository.getCommunityList()
    }
}
