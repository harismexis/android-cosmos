package com.harismexis.cosmos.home.viewholder.factory

import android.view.LayoutInflater
import com.harismexis.cosmos.apod.repository.APODRxRepo
import com.harismexis.cosmos.apod.viewholder.APODHomeVh
import com.harismexis.cosmos.apod.viewmodel.APODEntryVm
import com.harismexis.cosmos.databinding.VhHomeRowBinding
import com.harismexis.cosmos.databinding.VhHomeSimpleRowBinding
import com.harismexis.cosmos.home.adapter.HomeAdapter
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.mrp.repository.MRPRxRepo
import com.harismexis.cosmos.mrp.viewholder.MRPHomeVh
import com.harismexis.cosmos.mrp.viewmodel.MRPEntryVm
import com.harismexis.cosmos.niavl.viewholder.NIAVLHomeVh

fun createHomeViewHolder(
    viewType: Int,
    inflater: LayoutInflater,
    clickListener: HomeClickListener
): BaseHomeVh {
    return when (viewType) {
        HomeAdapter.VIEW_TYPE_APOND -> APODHomeVh(
            VhHomeRowBinding.inflate(inflater),
            APODEntryVm(APODRxRepo()),
            clickListener
        )
        HomeAdapter.VIEW_TYPE_MRP -> MRPHomeVh(
            VhHomeRowBinding.inflate(inflater),
            MRPEntryVm(MRPRxRepo()),
            clickListener
        )
        else -> NIAVLHomeVh(
            VhHomeSimpleRowBinding.inflate(inflater),
            clickListener
        )
    }
}
