package com.hv.community.android.domain.usecase.authentication

import com.hv.community.android.domain.repository.AuthenticationRepository
import javax.inject.Inject

class RefreshAccessTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authenticationRepository.getAccessToken(
            authenticationRepository.refreshToken
        ).onSuccess {
            authenticationRepository.accessToken = it
        }.map { }
    }
}
