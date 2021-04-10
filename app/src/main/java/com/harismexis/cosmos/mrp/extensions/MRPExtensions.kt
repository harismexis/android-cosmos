package com.harismexis.cosmos.mrp.extensions

import android.util.Log
import com.harismexis.cosmos.mrp.model.response.LatestMRP
import com.harismexis.cosmos.mrp.model.response.MRPItem
import com.harismexis.cosmos.mrp.model.ui.MRPUiModel

private val TAG = "MRPExtensions"

fun LatestMRP?.toUiModels(): List<MRPUiModel> {
    this?.let {
        return it.latest_photos.toUiModels()
    }
    throw IllegalStateException("Error parsing Latest MRP response")
}

fun MutableList<MRPItem?>?.toUiModels(): List<MRPUiModel> {
    val models: MutableList<MRPUiModel> = mutableListOf()
    this?.let {
        for (item in it) {
            if (!item.isValid()) Log.d(TAG, "MRP item is null or missing id / imgSrc")
            else {
                models.add(
                    MRPUiModel(
                        item!!.id!!,
                        item.sol,
                        item.camera,
                        item.imgSrc!!,
                        item.earthDate,
                        item.rover
                    )
                )
            }
        }
    }
    return models.toList()
}

fun MRPItem?.isValid(): Boolean {
    return this != null && this.id != null && !this.imgSrc.isNullOrBlank()
}