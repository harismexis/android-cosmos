package com.example.cosmos.mrp.model.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class Rover(
    var id: Int,
    var name: String,
    @SerializedName("landing_date")
    var landingDate: Date,
    @SerializedName("launch_date")
    var launchDate: Date,
    var status: String
)