package com.harismexis.cosmos.apod.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.harismexis.cosmos.R
import com.harismexis.cosmos.apod.model.APOD
import com.harismexis.cosmos.apod.viewmodel.APODEntryVm
import com.harismexis.cosmos.databinding.VhHomeRowBinding
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.workshared.enums.RowType
import com.harismexis.cosmos.workshared.extensions.getErrorMessage

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
        populateImage(item.hdurl)
        viewBinding.txtTitle.text = itemView.context.getString(R.string.vh_home_apod_title)
        viewBinding.txtMeta.text = item.title
    }

    private fun populateImage(url: String?) {
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewBinding.imgContainer)
    }

    override fun unbind() {
        viewModel.unbind()
    }

}