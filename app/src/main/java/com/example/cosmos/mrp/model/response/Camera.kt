package com.example.cosmos.mrp.model.response

import com.google.gson.annotations.SerializedName

data class Camera(
    var id: Int,
    var name: String,
    @SerializedName("rover_id")
    var roverId: Int,
    @SerializedName("full_name")
    var fullName: String
)