package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.AuthenticationRepository
import com.hv.community.android.domain.repository.SignUpRepository
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return signUpRepository.signIn(
            email,
            password
        ).onSuccess {
            signUpRepository.email = email
            signUpRepository.password = password
            authenticationRepository.accessToken = it.accessToken
            authenticationRepository.refreshToken = it.refreshToken
        }.map { }
    }
}
