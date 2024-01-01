package com.hv.community.android.domain.usecase.user

import com.hv.community.android.domain.repository.UserRepository
import javax.inject.Inject

class UserGetLoginDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Pair<String, String> {
        return userRepository.email to userRepository.password
    }
}
