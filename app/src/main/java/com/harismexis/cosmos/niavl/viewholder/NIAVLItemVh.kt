package com.harismexis.cosmos.niavl.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.harismexis.cosmos.databinding.VhNiavlItemBinding
import com.harismexis.cosmos.niavl.model.ui.NIAVLUiModel

class NIAVLItemVh(
    private var binding: VhNiavlItemBinding,
    private var itemClickListener: NIAVLItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val tag = NIAVLItemVh::class.qualifiedName

    interface NIAVLItemClickListener {
        fun onNIAVLItemClick(item: NIAVLUiModel, position: Int)
    }

    fun bind(
        item: NIAVLUiModel,
        position: Int
    ) {
        populateImage(item.thumbUrl)
        binding.txt1.text = item.title
        binding.root.setOnClickListener {
            itemClickListener.onNIAVLItemClick(item, position)
        }
    }

    private fun populateImage(url: String?) {
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .override(binding.imgPhoto.height)
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imgPhoto)
    }

    fun unbind() {}
}