package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        nickname: String,
        password: String
    ): Result<Unit> {
        return userRepository.signUp(
            email,
            nickname,
            password
        ).onSuccess {
            userRepository.email = email
            userRepository.password = password
            userRepository.emailToken = it.token
        }.map { }
    }
}
