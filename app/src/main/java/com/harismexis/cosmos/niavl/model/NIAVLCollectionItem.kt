package com.harismexis.cosmos.niavl.model

data class NIAVLCollectionItem(
    val data: List<NIAVLCollectionItemDataEntry?>?,
    val href: String // "https://images-assets.nasa.gov/video/JSC-Orion-2021-GA_infographic_animation4k/collection.json"
)