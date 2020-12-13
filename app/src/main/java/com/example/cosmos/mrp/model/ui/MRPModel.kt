package com.example.cosmos.mrp.model.ui

/**
 * Model for an MRP response containing a list of Mars Rover Photo Items
 */
data class MRPModel(
    var mrpItems: MutableList<MRPItemModel>
)