package com.hv.community.android.data.remote.network.util

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.data.remote.network.model.error.ErrorRes
import com.hv.community.android.domain.model.error.InvalidStandardResponseException
import com.hv.community.android.domain.model.error.ServerException
import kotlinx.serialization.json.Json
import retrofit2.Response

fun <T : DataMapper<R>, R : Any> Response<T>.convertResponseToDomain(): Result<R> {
    return runCatching {
        if (isSuccessful) {
            return@runCatching body()?.toDomain()
                ?: throw InvalidStandardResponseException("Response Empty Body")
        } else {
            runCatching {
                errorBody()?.string()?.let {
                    Json.decodeFromString<ErrorRes>(it)
                } ?: throw InvalidStandardResponseException("Response Empty Body")
            }.onSuccess { errorRes ->
                throw ServerException(errorRes.code, errorRes.message)
            }

            throw InvalidStandardResponseException("Invalid Error Response")
        }
    }
}

fun <T> Response<T>.convertResponse(): Result<T> {
    return runCatching {
        if (isSuccessful) {
            return@runCatching body()
                ?: throw InvalidStandardResponseException("Response Empty Body")
        } else {
            runCatching {
                errorBody()?.string()?.let {
                    Json.decodeFromString<ErrorRes>(it)
                } ?: throw InvalidStandardResponseException("Response Empty Body")
            }.onSuccess { errorRes ->
                throw ServerException(errorRes.code, errorRes.message)
            }

            throw InvalidStandardResponseException("Invalid Error Response")
        }
    }
}
