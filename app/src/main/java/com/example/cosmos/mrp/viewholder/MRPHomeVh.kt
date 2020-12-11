package com.example.cosmos.mrp.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.R
import com.example.cosmos.databinding.HomeMrpRowBinding
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewholder.BaseHomeVh
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.viewmodel.MRPEntryVm
import com.example.cosmos.workshared.extensions.getErrorMessage

class MRPHomeVh(
    private var binding: HomeMrpRowBinding,
    private var viewModel: MRPEntryVm,
    rowClickListener: HomeClickListener
) : BaseHomeVh(binding, rowClickListener) {

    private val TAG = MRPHomeVh::class.qualifiedName
    private var imgPhoto: ImageView = itemView.findViewById(R.id.imgContainer)
    private var txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
    private var txtDate: TextView = itemView.findViewById(R.id.txtDate)

    override fun bind(
        item: Any?,
        position: Int
    ) {
        super.bind(item, position)
        viewModel.getCuriosityLatestMRP(
            { updateUI(it) },
            { Log.d(TAG, it.getErrorMessage()) }
        )
    }

    private fun updateUI(item: MRPItem) {
        populateImage(item.imgSrc)
        txtTitle.text = item.id.toString()
        txtDate.text = item.earthDate.toString()
    }

    private fun populateImage(url: String?) {
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgPhoto)
    }

    override fun unbind() {
        viewModel.unbind()
    }

}