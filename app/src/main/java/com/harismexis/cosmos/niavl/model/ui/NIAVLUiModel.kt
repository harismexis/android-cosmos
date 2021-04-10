package com.harismexis.cosmos.niavl.model.ui

data class NIAVLUiModel (
    val nasaId: String,
    val mediaType: String,
    val title: String?,
    val description: String?,
    val dateCreated: String?,
    val href: String,
    val thumbUrl: String?
)