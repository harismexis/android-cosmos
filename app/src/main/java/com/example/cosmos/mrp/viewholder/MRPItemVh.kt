package com.example.cosmos.mrp.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cosmos.databinding.MrpItemBinding
import com.example.cosmos.mrp.model.response.MRPItem

class MRPItemVh(
    private var viewBinding: MrpItemBinding,
    private var itemClickListener: MRPItemClickListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    private val TAG = MRPItemVh::class.qualifiedName

    interface MRPItemClickListener {
        fun onMRPItemClick(item: MRPItem, position: Int)
    }

    fun bind(
        item: MRPItem?,
        position: Int
    ) {
        populateImage(item?.imgSrc)
        viewBinding.txtMeta.text = item?.sol.toString()
//        viewBinding.root.setOnClickListener {
//            item?.also {
//                itemClickListener.onMRPItemClick(it, position)
//            } ?: run {
//                Log.d(TAG, "Null MRP item")
//            }
//        }
    }

    private fun populateImage(url: String?) {
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewBinding.imgPhoto)
    }

    fun unbind() {}
}