package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserHasEmailTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Boolean {
        return userRepository.emailToken.isNotEmpty()
    }
}
