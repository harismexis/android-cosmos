package com.example.cosmos.home.viewholder.factory

import android.view.LayoutInflater
import com.example.cosmos.apod.repository.APODRxRepo
import com.example.cosmos.apod.viewholder.APODHomeVh
import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.databinding.HomeApodRowBinding
import com.example.cosmos.databinding.HomeMrpRowBinding
import com.example.cosmos.home.adapter.HomeAdapter
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewholder.BaseHomeVh
import com.example.cosmos.mrp.repository.MRPRxRepo
import com.example.cosmos.mrp.viewholder.MRPHomeVh
import com.example.cosmos.mrp.viewmodel.MRPEntryVm

fun createHomeViewHolder(
    viewType: Int,
    inflater: LayoutInflater,
    clickListener: HomeClickListener
): BaseHomeVh {
    return when (viewType) {
        HomeAdapter.VIEW_TYPE_APOND -> APODHomeVh(
            HomeApodRowBinding.inflate(inflater),
            APODEntryVm(APODRxRepo()),
            clickListener
        )
        else -> MRPHomeVh(
            HomeMrpRowBinding.inflate(inflater),
            MRPEntryVm(MRPRxRepo()),
            clickListener
        )
    }
}
