package com.hv.community.android.data.repository

import com.hv.community.android.domain.model.SampleInformation
import com.hv.community.android.domain.repository.SampleRepository

class MockSampleRepository : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return Result.success(SampleInformation())
    }
}
