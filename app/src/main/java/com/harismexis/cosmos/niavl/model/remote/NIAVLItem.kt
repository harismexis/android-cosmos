package com.harismexis.cosmos.niavl.model.remote

data class NIAVLItem(
    val data: List<NIAVLDataEntry?>?,
    val href: String?, // link to get json with urls for image, video, audio
    val links: List<NIAVLLink?>?
)