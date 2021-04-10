package com.harismexis.cosmos.mrp.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.VhHomeRowBinding
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.mrp.model.response.MRPItem
import com.harismexis.cosmos.mrp.viewmodel.MRPEntryVm
import com.harismexis.cosmos.workshared.enums.RowType
import com.harismexis.cosmos.workshared.extensions.getErrorMessage

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
        populateImage(item.imgSrc)
        binding.txtTitle.text = itemView.context.getString(R.string.vh_home_mrp_title)
        binding.txtMeta.text = item.earthDate.toString()
    }

    private fun populateImage(url: String?) {
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .error(ColorDrawable(Color.BLACK))
            .placeholder(R.drawable.loading_animation)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(binding.imgContainer)
    }

    override fun unbind() {
        viewModel.unbind()
    }

}