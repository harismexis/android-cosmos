package com.harismexis.cosmos.niavl.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.harismexis.cosmos.databinding.VhNiavlItemBinding
import com.harismexis.cosmos.niavl.model.NIAVLCollectionItem

class NIAVLItemVh(
    private var binding: VhNiavlItemBinding,
    private var itemClickListener: NIAVLItemVh.NIAVLItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val tag = NIAVLItemVh::class.qualifiedName

    interface NIAVLItemClickListener {
        fun onNIAVLItemClick(item: NIAVLCollectionItem, position: Int)
    }

    fun bind(
        item: NIAVLCollectionItem,
        position: Int
    ) {
        populateImage(item.href)
//        binding.txt1.text = item.earthDate.toString()
//        binding.txt2.text = itemView.context.getString(R.string.vh_mrp_item_id_txt, item.id)
        binding.root.setOnClickListener {
            itemClickListener.onNIAVLItemClick(item, position)
        }
    }

    private fun populateImage(url: String?) {
        binding.imgPhoto.layout(0, 0, 0, 0)
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .override(binding.imgPhoto.height)
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imgPhoto)
    }

    fun unbind() {}
}