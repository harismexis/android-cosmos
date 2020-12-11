package com.example.cosmos.mrp.model.response

import com.google.gson.annotations.SerializedName

data class LatestMRPResponse(
    @SerializedName("latest_photos")
    var latest_photos: MutableList<MarsPhoto>?
)