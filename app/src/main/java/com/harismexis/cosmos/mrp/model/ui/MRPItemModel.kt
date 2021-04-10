package com.harismexis.cosmos.mrp.model.ui

import com.harismexis.cosmos.mrp.model.response.Camera
import com.harismexis.cosmos.mrp.model.response.Rover
import java.util.*

/**
 * Model for a Mars Rover Photo Item
 */
data class MRPItemModel(
    var id: Int,
    var sol: Int?,
    var camera: Camera?,
    var imgSrc: String,
    var earthDate: Date?,
    var rover: Rover?
)