package com.example.cosmos.mrp.extensions

import com.example.cosmos.mrp.model.response.LatestMRP
import com.example.cosmos.mrp.model.response.MRP
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.model.ui.MRPUiModel

fun MRP?.toUiModel(): MRPUiModel {
    this?.let {
        it.photos?.let { items ->
            val models: MutableList<MRPItem> = mutableListOf()
            models.addAll(items)
            return MRPUiModel(models)
        }
    }
    throw IllegalStateException("Error parsing MRPResponse")
}

fun LatestMRP?.toUiModel(): MRPUiModel {
    this?.let {
        it.latest_photos?.let { items ->
            val models: MutableList<MRPItem> = mutableListOf()
            models.addAll(items)
            return MRPUiModel(models)
        }
    }
    throw IllegalStateException("Error parsing LatestMRPResponse")
}