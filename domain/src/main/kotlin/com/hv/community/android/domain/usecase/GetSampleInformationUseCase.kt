package com.hv.community.android.domain.usecase

import com.hv.community.android.domain.model.SampleInformation
import com.hv.community.android.domain.repository.SampleRepository
import javax.inject.Inject

class GetSampleInformationUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    suspend operator fun invoke(): Result<SampleInformation> {
        return sampleRepository.getSampleInformation(
            apiKey = "",
            title = "",
            artist = ""
        )
    }
}
