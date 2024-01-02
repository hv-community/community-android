package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.AuthenticationRepository
import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return userRepository.signIn(
            email,
            password
        ).onSuccess {
            userRepository.email = email
            userRepository.password = password
            authenticationRepository.accessToken = it.accessToken
            authenticationRepository.refreshToken = it.refreshToken
        }.map { }
    }
}
