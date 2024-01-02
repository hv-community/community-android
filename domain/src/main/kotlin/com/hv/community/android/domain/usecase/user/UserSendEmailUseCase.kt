package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserSendEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return userRepository.sendEmail(
            userRepository.emailToken
        ).map { }
    }
}
