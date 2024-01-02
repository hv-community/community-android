package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserVerifyEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        verificationCode: String
    ): Result<Unit> {
        return userRepository.verifyEmail(
            userRepository.emailToken,
            verificationCode
        ).onSuccess {
            userRepository.emailToken = ""
        }.map { }
    }
}
