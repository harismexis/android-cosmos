package com.harismexis.cosmos.mrp.viewholder

import android.util.Log
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.VhHomeRowBinding
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.mrp.model.response.MRPItem
import com.harismexis.cosmos.mrp.viewmodel.MRPEntryVm
import com.harismexis.cosmos.workshared.enums.RowType
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import com.harismexis.cosmos.workshared.extensions.populateWithGlide

class MRPHomeVh(
    private var binding: VhHomeRowBinding,
    private var viewModel: MRPEntryVm,
    rowClickListener: HomeClickListener
) : BaseHomeVh(binding, rowClickListener) {

    private val TAG = MRPHomeVh::class.qualifiedName

    override fun bind(
        rowType: RowType,
        position: Int
    ) {
        super.bind(rowType, position)
        viewModel.getCuriosityLatestMRP(
            { updateUI(it) },
            { Log.d(TAG, it.getErrorMessage()) }
        )
    }

    private fun updateUI(item: MRPItem) {
        itemView.context.populateWithGlide(binding.imgv, item.imgSrc)
        binding.txtTitle.text = itemView.context.getString(R.string.vh_home_mrp_title)
        binding.txtMeta.text = item.earthDate.toString()
    }

    override fun unbind() {
        viewModel.unbind()
    }

}