package com.example.cosmos.apod.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.R
import com.example.cosmos.apod.model.APODResponse
import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.databinding.HomeApodRowBinding
import com.example.cosmos.home.ui.viewholder.BaseHomeVh
import com.example.cosmos.mrp.model.response.MarsPhoto

class APODHomeVh(
    var binding: HomeApodRowBinding,
    var viewModel: APODEntryVm
) : BaseHomeVh(binding) {

    private var imgPhoto: ImageView = itemView.findViewById(R.id.imgMarsPhoto)
    private var txtMeta: TextView = itemView.findViewById(R.id.txtMeta)

    // private lateinit var viewModel: APODEntryVm

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    fun bind(
        item: MarsPhoto?,
        position: Int
    ) {
        Glide.with(itemView.context)
            .load(Uri.parse(item?.imgSrc))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgPhoto)

        txtMeta.text = item?.sol.toString()
    }

    override fun bind(item: Any?, position: Int) {

    }

    override fun unbind() {}

    private fun updateUI(apod: APODResponse) {
//        apod.hdurl?.let {
//            loadImage(it)
//        }
//        apodBinding.txtTitle.text = apod.title
    }

}