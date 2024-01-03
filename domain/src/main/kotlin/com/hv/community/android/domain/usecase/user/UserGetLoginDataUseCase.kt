package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.SignUpRepository
import javax.inject.Inject

class UserGetLoginDataUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    operator fun invoke(): Pair<String, String> {
        return signUpRepository.email to signUpRepository.password
    }
}
