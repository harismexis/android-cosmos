package com.example.cosmos.marsroverphotos.api

import com.example.cosmos.marsroverphotos.model.response.LatestMRPResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Mars Rover Photos API
 * https://api.nasa.gov/
 * https://apod.nasa.gov/apod/astropix.html
 */
interface MRPApi {

//    @GET("rovers/{roverName}/photos")
//    suspend fun queryByMartianSol(
//        @Path("roverName") roverName: String,
//        @Query("sol") sol: Int,
//        @Query("camera") camera: String?,
//        @Query("page") page: Int?,
//        @Query("api_key") apiKey: String
//    ): MRPResponse?
//
//    @GET("rovers/{roverName}/photos")
//    suspend fun queryByEarthDate(
//        @Path("roverName") roverName: String,
//        @Query("earth_date") earthDate: Date,
//        @Query("camera") camera: String?,
//        @Query("page") page: Int?,
//        @Query("api_key") apiKey: String
//    ): MRPResponse?
//
//    @GET("rovers/{roverName}/latest_photos")
//    suspend fun queryByLatestPhotos(
//        @Path("roverName") roverName: String,
//        @Query("api_key") apiKey: String
//    ): MRPResponse?

    @GET("curiosity/latest_photos")
    suspend fun queryCuriosityByLatestPhotos(
        @Query("api_key") apiKey: String
    ): LatestMRPResponse?

}