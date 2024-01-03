package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.SignUpRepository
import javax.inject.Inject

class UserVerifyEmailUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        verificationCode: String
    ): Result<Unit> {
        return signUpRepository.verifyEmail(
            signUpRepository.emailToken,
            verificationCode
        ).onSuccess {
            signUpRepository.emailToken = ""
        }.map { }
    }
}
