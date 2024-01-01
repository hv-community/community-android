package com.hv.community.android.presentation.model

import android.os.Parcelable
import com.hv.community.android.domain.model.SampleInformation
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleInformationModel(
    val lyricsId: Int = 0,
    val lyricsBody: String = ""
) : Parcelable

// TODO : 개선 방법 찾기
fun SampleInformation.toUiModel(): SampleInformationModel {
    return SampleInformationModel(lyricsId, lyricsBody)
}
