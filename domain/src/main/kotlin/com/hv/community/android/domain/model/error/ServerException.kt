package com.hv.community.android.domain.model.error

class ServerException(
    val code: Int,
    override val message: String
) : Exception(message)
