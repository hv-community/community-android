package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.AuthenticationRepository
import javax.inject.Inject

class UserIsLoginedUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(): Boolean {
        return authenticationRepository.accessToken.isNotEmpty()
                && authenticationRepository.refreshToken.isNotEmpty()
    }
}
