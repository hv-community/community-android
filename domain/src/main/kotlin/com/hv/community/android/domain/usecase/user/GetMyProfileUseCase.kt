package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.model.user.UserProfile
import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class GetMyProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<UserProfile> {
        return userRepository.getProfile()
    }
}
