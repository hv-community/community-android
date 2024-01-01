package com.hv.community.android.data.repository

import com.hv.community.android.data.remote.network.api.SampleApi
import com.hv.community.android.data.remote.network.util.convertResponseToDomain
import com.hv.community.android.domain.model.SampleInformation
import com.hv.community.android.domain.repository.SampleRepository

class RealSampleRepository(
    private val sampleApi: SampleApi
) : SampleRepository {
    override suspend fun getSampleInformation(
        apiKey: String,
        title: String,
        artist: String
    ): Result<SampleInformation> {
        return sampleApi.getSampleInformation(
            apiKey = apiKey,
            title = title,
            artist = artist
        ).convertResponseToDomain()
    }
}
