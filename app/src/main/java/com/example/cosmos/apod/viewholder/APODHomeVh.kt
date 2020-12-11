package com.example.cosmos.apod.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.databinding.ApodHomeRowBinding
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewholder.BaseHomeVh
import com.example.cosmos.workshared.enums.RowType
import com.example.cosmos.workshared.extensions.getErrorMessage

class APODHomeVh(
    private var viewBinding: ApodHomeRowBinding,
    private var viewModel: APODEntryVm,
    rowClickListener: HomeClickListener
) : BaseHomeVh(viewBinding, rowClickListener) {

    private val TAG = APODHomeVh::class.qualifiedName

    override fun bind(
        rowType: RowType,
        position: Int
    ) {
        super.bind(rowType, position)
        viewModel.getAPOD(
            { updateUI(it) },
            { Log.d(TAG, it.getErrorMessage()) }
        )
    }

    private fun updateUI(item: APOD) {
        populateImage(item.hdurl)
        viewBinding.txtTitle.text = item.title
        viewBinding.txtDate.text = item.date
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