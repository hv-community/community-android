package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.SignUpRepository
import javax.inject.Inject

class UserSignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        email: String,
        nickname: String,
        password: String
    ): Result<Unit> {
        return signUpRepository.signUp(
            email,
            nickname,
            password
        ).onSuccess {
            signUpRepository.email = email
            signUpRepository.password = password
            signUpRepository.emailToken = it.token
        }.map { }
    }
}
