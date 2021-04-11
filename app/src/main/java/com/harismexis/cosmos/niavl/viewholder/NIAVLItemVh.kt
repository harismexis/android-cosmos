package com.harismexis.cosmos.niavl.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.cosmos.databinding.VhNiavlItemBinding
import com.harismexis.cosmos.niavl.model.ui.NIAVLUiModel
import com.harismexis.cosmos.workshared.extensions.populateWithGlide

class NIAVLItemVh(
    private var binding: VhNiavlItemBinding,
    private var itemClickListener: NIAVLItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface NIAVLItemClickListener {
        fun onNIAVLItemClick(item: NIAVLUiModel, position: Int)
    }

    fun bind(
        item: NIAVLUiModel,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgPhoto, item.thumbUrl)
        binding.txt1.text = item.nasaId
        binding.root.setOnClickListener {
            itemClickListener.onNIAVLItemClick(item, position)
        }
    }

    fun unbind() {}
}