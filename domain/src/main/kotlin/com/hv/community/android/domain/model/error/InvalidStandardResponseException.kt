package com.hv.community.android.domain.model.error

class InvalidStandardResponseException(
    override val message: String
) : Exception(message)
