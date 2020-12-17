package com.example.cosmos.workshared.extensions

import com.example.cosmos.apod.model.APOD

fun APOD.hasHdUrl(): Boolean {
    return !this.hdurl.isNullOrBlank()
}