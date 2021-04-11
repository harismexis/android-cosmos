package com.harismexis.cosmos.niavl.api

import retrofit2.http.GET
import retrofit2.http.Url

/**
 *
 * API to get a collection of media urls for a specific item
 * of NASA Images and Videos Library
 */
interface MediaCollectionApi {

    @GET
    suspend fun getMediaCollection(
        @Url url: String
    ): List<String>

}