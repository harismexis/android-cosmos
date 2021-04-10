package com.harismexis.cosmos.niavl.extensions

import com.harismexis.cosmos.niavl.model.remote.NIAVLItem
import com.harismexis.cosmos.niavl.model.remote.NIAVLLink
import com.harismexis.cosmos.niavl.model.remote.NIAVLResponse
import com.harismexis.cosmos.niavl.model.ui.NIAVLUiModel

private val TAG = "MRPExtensions"

fun NIAVLResponse?.toUiModels(): List<NIAVLUiModel> {
    val models: MutableList<NIAVLUiModel> = mutableListOf()
    val filtered = this?.collection?.items?.filter { it.isValid() }?.map {
        NIAVLUiModel(
            it!!.data!![0]!!.nasaId!!,
            it.data!![0]!!.mediaType!!,
            it.data[0]!!.title,
            it.data[0]!!.description,
            it.data[0]!!.dateCreated,
            it.href!!,
            it.thumbUrl()
        )
    }
    filtered?.let {
        models.addAll(it)
    }
    return models.toList()
}

private fun NIAVLItem?.isValid(): Boolean {
    this?.let { item ->
        if (item.href.isNullOrEmpty()) return false
        item.data?.let {
            return it.isNotEmpty() &&
                    !it[0]?.nasaId.isNullOrEmpty() &&
                    !it[0]?.mediaType.isNullOrEmpty()
        }
    }
    return false
}

private fun NIAVLItem?.thumbUrl(): String? {
    this?.links?.let {
        for (_link in it) {
            _link?.let { link ->
                if (link.rel == NIAVLLink.PREVIEW)
                    return link.href
            }
        }
    }
    return null
}



