package com.example.cosmos.home.ui.viewholder.factory

import android.view.LayoutInflater
import com.example.cosmos.apod.repository.APODRxRepo
import com.example.cosmos.apod.viewholder.APODHomeVh
import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.databinding.HomeApodRowBinding
import com.example.cosmos.databinding.HomeMrpRowBinding
import com.example.cosmos.home.ui.adapter.HomeAdapter
import com.example.cosmos.home.ui.viewholder.BaseHomeVh
import com.example.cosmos.mrp.viewholder.MRPHomeVh

fun createHomeViewHolder(
    viewType: Int,
    inflater: LayoutInflater
): BaseHomeVh {
    return when (viewType) {
        HomeAdapter.VIEW_TYPE_APOND -> APODHomeVh(
            HomeApodRowBinding.inflate(inflater),
            APODEntryVm(APODRxRepo())
        )
        else -> MRPHomeVh(
            HomeMrpRowBinding.inflate(inflater)
        )
    }
}
