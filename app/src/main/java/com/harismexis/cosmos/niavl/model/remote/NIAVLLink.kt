package com.harismexis.cosmos.niavl.model.remote

data class NIAVLLink(
    val rel: String, //"preview",
    val href: String, // "https://images-assets.nasa.gov/video/JSC-Orion-2021-GA_infographic_animation4k/JSC-Orion-2021-GA_infographic_animation4k~thumb.jpg",
    val render: String, // "image"
) {
    companion object {
        const val PREVIEW = "preview"
    }
}