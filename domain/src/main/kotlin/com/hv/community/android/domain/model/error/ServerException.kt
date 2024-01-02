package com.hv.community.android.domain.model.error

class ServerException(
    val id: String,
    override val message: String
) : Exception(message)
