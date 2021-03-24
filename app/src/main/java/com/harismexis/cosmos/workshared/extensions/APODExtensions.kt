package com.harismexis.cosmos.workshared.extensions

import com.harismexis.cosmos.apod.model.APOD

fun APOD.hasHdUrl(): Boolean {
    return !this.hdurl.isNullOrBlank()
}