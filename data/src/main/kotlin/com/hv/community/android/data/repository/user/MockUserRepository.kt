package com.hv.community.android.data.repository.user

import com.hv.community.android.domain.model.user.UserProfile
import com.hv.community.android.domain.repository.UserRepository
import kotlinx.coroutines.delay

class MockUserRepository : UserRepository {
    override suspend fun getProfile(): Result<UserProfile> {
        randomShortDelay()
        return Result.success(
            UserProfile(
                id = 1L,
                email = "asdf@gmail.com",
                nickname = "asdf"
            )
        )
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 500).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }
}
