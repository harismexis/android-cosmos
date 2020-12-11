package com.example.cosmos.mrp.model.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class MarsPhoto(
    var id: Int,
    var sol: Int,
    var camera: Camera,
    @SerializedName("img_src")
    var imgSrc: String,
    @SerializedName("earth_date")
    var earthDate: Date,
    var rover: Rover
)