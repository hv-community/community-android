package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.AuthenticationRepository
import com.hv.community.android.domain.repository.SignUpRepository
import io.sentry.Sentry
import io.sentry.protocol.User
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val authenticationRepository: AuthenticationRepository,
    private val getMyProfileUseCase: GetMyProfileUseCase
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
            getMyProfileUseCase().onSuccess { profile ->
                // TODO : 분리하기
                Sentry.setUser(
                    User().apply {
                        this.id = profile.id.toString()
                        this.email = profile.email
                        this.username = profile.nickname
                    }
                )
            }.getOrThrow()
        }.map { }
    }
}
