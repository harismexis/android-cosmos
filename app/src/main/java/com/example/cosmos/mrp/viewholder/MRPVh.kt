package com.example.cosmos.mrp.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.R
import com.example.cosmos.mrp.model.response.MRPItem

class MRPVh(
    view: View
) : RecyclerView.ViewHolder(view) {

    private var imgPhoto: ImageView = itemView.findViewById(R.id.imgMarsPhoto)
    private var txtMeta: TextView = itemView.findViewById(R.id.txtMeta)

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    fun bind(
        item: MRPItem?,
        position: Int
    ) {
        Glide.with(itemView.context)
            .load(Uri.parse(item?.imgSrc))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgPhoto)

        txtMeta.text = item?.sol.toString()
    }

    fun unbind() {}
}