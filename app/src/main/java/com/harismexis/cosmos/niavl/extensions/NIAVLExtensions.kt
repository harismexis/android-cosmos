package com.harismexis.cosmos.niavl.extensions

import com.harismexis.cosmos.niavl.model.NIAVLCollectionItem
import com.harismexis.cosmos.niavl.model.NIAVLResponse

private val TAG = "MRPExtensions"

fun NIAVLResponse?.toUiModels(): List<NIAVLCollectionItem> {
    val models: MutableList<NIAVLCollectionItem> = mutableListOf()
    val filtered = this?.collection?.items?.filter { it?.href != null }?.toList()
    filtered?.let {
        for (item in filtered) {
            models.add(item!!)
        }
    }
    return models.toList()
}