package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.SignUpRepository
import javax.inject.Inject

class UserHasEmailTokenUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    operator fun invoke(): Boolean {
        return signUpRepository.emailToken.isNotEmpty()
    }
}
