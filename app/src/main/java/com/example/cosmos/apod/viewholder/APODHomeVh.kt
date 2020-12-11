package com.example.cosmos.apod.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.R
import com.example.cosmos.apod.model.APOD
import com.example.cosmos.apod.viewmodel.APODEntryVm
import com.example.cosmos.databinding.HomeApodRowBinding
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.home.viewholder.BaseHomeVh
import com.example.cosmos.workshared.enums.RowType
import com.example.cosmos.workshared.extensions.getErrorMessage

class APODHomeVh(
    private var binding: HomeApodRowBinding,
    private var viewModel: APODEntryVm,
    rowClickListener: HomeClickListener
) : BaseHomeVh(binding, rowClickListener) {

    private val TAG = APODHomeVh::class.qualifiedName
    private var imgPhoto: ImageView = itemView.findViewById(R.id.imgContainer)
    private var txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
    private var txtDate: TextView = itemView.findViewById(R.id.txtDate)

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
        txtTitle.text = item.title
        txtDate.text = item.date
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