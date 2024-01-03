package com.hv.community.android.domain.repository

import com.hv.community.android.domain.model.user.UserProfile

interface UserRepository {
    suspend fun getProfile(): Result<UserProfile>
}
