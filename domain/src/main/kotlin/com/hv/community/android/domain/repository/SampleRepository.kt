package com.hv.community.android.domain.repository

import com.hv.community.android.domain.model.SampleInformation

interface SampleRepository {
    suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation>
}
