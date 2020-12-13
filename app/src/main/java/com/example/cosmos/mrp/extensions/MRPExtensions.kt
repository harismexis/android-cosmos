package com.example.cosmos.mrp.extensions

import android.util.Log
import com.example.cosmos.mrp.model.response.LatestMRP
import com.example.cosmos.mrp.model.response.MRP
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.model.ui.MRPItemModel
import com.example.cosmos.mrp.model.ui.MRPModel

private val TAG = "MRPExtensions"

fun MRP?.toUiModel(): MRPModel {
    this?.let {
        return it.photos.toUiModel()
    }
    throw IllegalStateException("Error parsing MRP response")
}

fun LatestMRP?.toUiModel(): MRPModel {
    this?.let {
        return it.latest_photos.toUiModel()
    }
    throw IllegalStateException("Error parsing Latest MRP response")
}

fun MutableList<MRPItem?>?.toUiModel(): MRPModel {
    this?.let {
        val models: MutableList<MRPItemModel> = mutableListOf()
        for (item in it) {
            if (!item.isValid()) {
                Log.d(TAG, "MRP item is null or missing id / imgSrc")
            } else {
                models.add(
                    MRPItemModel(
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
        return MRPModel(models)
    }
    throw IllegalStateException("Error parsing MRPResponse")
}

fun MRPItem?.isValid(): Boolean {
    return this != null && this.id != null && !this.imgSrc.isNullOrBlank()
}