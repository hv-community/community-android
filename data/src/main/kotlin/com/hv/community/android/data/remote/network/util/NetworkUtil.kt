package com.hv.community.android.data.remote.network.util

import com.hv.community.android.data.mapper.DataMapper
import com.hv.community.android.data.remote.network.model.error.ErrorRes
import com.hv.community.android.domain.model.error.InvalidStandardResponseException
import com.hv.community.android.domain.model.error.ServerException
import kotlinx.serialization.json.Json
import retrofit2.Response
import timber.log.Timber

fun <T : DataMapper<R>, R : Any> Response<T>.convertResponseToDomain(
    errorMessageMapper: (String) -> String
): Result<R> {
    return runCatching {
        if (isSuccessful) {
            return@runCatching body()?.toDomain() ?: throw InvalidStandardResponseException("Response Empty Body")
        } else {
            throw this.toThrowable(errorMessageMapper)
        }
    }.onFailure { exception ->
        Timber.d(exception)
    }
}

fun <T> Response<T>.convertResponse(
    errorMessageMapper: (String) -> String
): Result<T> {
    return runCatching {
        if (isSuccessful) {
            return@runCatching body() ?: throw InvalidStandardResponseException("Response Empty Body")
        } else {
            throw this.toThrowable(errorMessageMapper)
        }
    }.onFailure { exception ->
        Timber.d(exception)
    }
}

private fun Response<*>.toThrowable(
    errorMessageMapper: (String) -> String
): Throwable {
    return runCatching {
        errorBody()?.string()?.let {
            val errorRes = Json.decodeFromString<ErrorRes>(it)
            ServerException(errorRes.id, errorMessageMapper(errorRes.id))
        } ?: InvalidStandardResponseException("Response Empty Body")
    }.getOrElse { exception ->
        exception
    }
}
