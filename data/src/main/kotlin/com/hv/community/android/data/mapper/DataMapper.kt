package com.hv.community.android.data.mapper

interface DataMapper<D> {
    fun toDomain(): D
}