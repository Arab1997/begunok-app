package com.reactive.begunok.utils

import com.reactive.begunok.BuildConfig

object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val TIMEOUT = 10.toLong()
}

data class KeyValue(val key: String, var value: String)
