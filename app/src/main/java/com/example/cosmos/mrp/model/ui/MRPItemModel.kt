package com.example.cosmos.mrp.model.ui

import com.example.cosmos.mrp.model.response.Camera
import com.example.cosmos.mrp.model.response.Rover
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Model for a Mars Rover Photo Item
 */
data class MRPItemModel(
    var id: Int,
    var sol: Int?,
    var camera: Camera?,
    @SerializedName("img_src")
    var imgSrc: String,
    @SerializedName("earth_date")
    var earthDate: Date?,
    var rover: Rover?
)