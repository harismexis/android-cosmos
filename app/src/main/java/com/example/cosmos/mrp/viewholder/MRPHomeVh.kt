package com.example.cosmos.mrp.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.R
import com.example.cosmos.databinding.HomeRowBinding
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewholder.BaseHomeVh
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.viewmodel.MRPEntryVm
import com.example.cosmos.workshared.enums.RowType
import com.example.cosmos.workshared.extensions.getErrorMessage

class MRPHomeVh(
    private var binding: HomeRowBinding,
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
        populateImage(item.imgSrc)
        binding.txtTitle.text = itemView.context.getString(R.string.vh_home_apod_title)
        binding.txtMeta.text = item.earthDate.toString()
    }

    private fun populateImage(url: String?) {
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgContainer)
    }

    override fun unbind() {
        viewModel.unbind()
    }

}