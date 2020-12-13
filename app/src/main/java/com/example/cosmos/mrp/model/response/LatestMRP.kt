package com.example.cosmos.mrp.model.response

/**
 * Model for a Latest MRP response containing a list of Mars Rover Photo Items
 */
data class LatestMRP(
    var latest_photos: MutableList<MRPItem?>?
)