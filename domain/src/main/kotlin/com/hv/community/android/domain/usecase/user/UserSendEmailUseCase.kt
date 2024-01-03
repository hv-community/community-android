package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.SignUpRepository
import javax.inject.Inject

class UserSendEmailUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return signUpRepository.sendEmail(
            signUpRepository.emailToken
        ).map { }
    }
}
