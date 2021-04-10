package com.harismexis.cosmos.apod.viewholder

import android.util.Log
import com.harismexis.cosmos.R
import com.harismexis.cosmos.apod.model.APOD
import com.harismexis.cosmos.apod.viewmodel.APODEntryVm
import com.harismexis.cosmos.databinding.VhHomeRowBinding
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.workshared.enums.RowType
import com.harismexis.cosmos.workshared.extensions.getErrorMessage
import com.harismexis.cosmos.workshared.extensions.populateWithGlide

class APODHomeVh(
    private var viewBinding: VhHomeRowBinding,
    private var viewModel: APODEntryVm,
    rowClickListener: HomeClickListener
) : BaseHomeVh(viewBinding, rowClickListener) {

    private val tag = APODHomeVh::class.qualifiedName

    override fun bind(
        rowType: RowType,
        position: Int
    ) {
        super.bind(rowType, position)
        viewModel.getHomeAPOD(
            { updateUI(it) },
            { Log.d(tag, it.getErrorMessage()) }
        )
    }

    private fun updateUI(item: APOD) {
        itemView.context.populateWithGlide(viewBinding.imgv, item.hdurl)
        viewBinding.txtTitle.text = itemView.context.getString(R.string.vh_home_apod_title)
        viewBinding.txtMeta.text = item.title
    }

    override fun unbind() {
        viewModel.unbind()
    }

}