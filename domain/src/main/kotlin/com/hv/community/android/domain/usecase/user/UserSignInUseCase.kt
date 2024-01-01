package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return userRepository.signIn(
            email,
            password
        ).onSuccess {
//            authRepository.accessToken = it.accessToken
//            authRepository.refreshToken = it.refreshToken
        }.map { }
    }
}
