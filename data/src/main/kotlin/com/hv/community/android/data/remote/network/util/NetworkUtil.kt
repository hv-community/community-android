package com.hv.community.android.data.remote.network.util

import com.hv.community.android.data.mapper.DataMapper
import retrofit2.Response

fun <T : DataMapper<R>, R : Any> Response<T>.convertResponseToDomain(): Result<R> {
    return runCatching {
        if (isSuccessful) {
            return@runCatching body()?.toDomain() ?: throw Exception("Response Empty Body")
        } else {
            // TODO : Error 형식이 주어져 있을 경우, 해당 규격에 맞게 에러 모델 변환
            throw Exception("Internal Server Error (Unexcepted Response)")
        }
    }
}
