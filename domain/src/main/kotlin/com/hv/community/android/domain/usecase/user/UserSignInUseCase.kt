package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.AuthenticationRepository
import com.hv.community.android.domain.repository.SignUpRepository
import com.hv.community.android.domain.repository.UserRepository
import io.sentry.Sentry
import io.sentry.protocol.User
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val userRepository: UserRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return signUpRepository.signIn(
            email,
            password
        ).onSuccess { token ->
            signUpRepository.email = email
            signUpRepository.password = password
            authenticationRepository.accessToken = token.accessToken
            authenticationRepository.refreshToken = token.refreshToken
            // TODO : 분리하기
            userRepository.getProfile().onSuccess { profile ->
                Sentry.setUser(
                    User().apply {
                        this.email = profile.email
                        this.username = profile.name
                    }
                )
            }.getOrThrow()
        }.map { }
    }
}
