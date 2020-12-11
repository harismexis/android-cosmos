package com.example.cosmos.mrp.extensions

import com.example.cosmos.mrp.model.response.LatestMRPResponse
import com.example.cosmos.mrp.model.response.MRPResponse
import com.example.cosmos.mrp.model.response.MarsPhoto
import com.example.cosmos.mrp.model.ui.MRPUiModel

fun MRPResponse?.toUiModel(): MRPUiModel {
    this?.let {
        it.photos?.let { items ->
            val models: MutableList<MarsPhoto> = mutableListOf()
            models.addAll(items)
            return MRPUiModel(models)
        }
    }
    throw IllegalStateException("Error parsing MRPResponse")
}

fun LatestMRPResponse?.toUiModel(): MRPUiModel {
    this?.let {
        it.latest_photos?.let { items ->
            val models: MutableList<MarsPhoto> = mutableListOf()
            models.addAll(items)
            return MRPUiModel(models)
        }
    }
    throw IllegalStateException("Error parsing LatestMRPResponse")
}