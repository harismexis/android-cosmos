package com.harismexis.cosmos.niavl.model

import com.google.gson.annotations.SerializedName

data class NIAVLCollectionItemDataEntry(
    val center: String?, // "JSC",
    val title: String?, // "JSC-Orion-2021-GA_infographic_animation4k",
    val description: String?, // "Orion split apart animation showing the Launch Abort System, Crew Module, and Service Module. Artemis I configuration.",
    @SerializedName("nasa_id")
    val nasaId: String?, // "JSC-Orion-2021-GA_infographic_animation4k",
    @SerializedName("media_type")
    val mediaType: String?, // "video",
    @SerializedName("date_created")
    val dateCreated: String? // "2021-03-29T00:00:00Z"
)