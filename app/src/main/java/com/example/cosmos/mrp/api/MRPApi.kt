package com.example.cosmos.mrp.api

import com.example.cosmos.mrp.model.response.LatestMRP
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Mars Rover Photos
 * NASA API
 * https://api.nasa.gov/
 * https://apod.nasa.gov/apod/astropix.html
 */
interface MRPApi {

    @GET("curiosity/latest_photos")
    suspend fun queryCuriosityByLatestMRP(
        @Query("api_key") apiKey: String
    ): LatestMRP?

    @GET("curiosity/latest_photos")
    fun queryCuriosityByLatestMRPSingle(
        @Query("api_key") apiKey: String
    ): Single<LatestMRP?>

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

}