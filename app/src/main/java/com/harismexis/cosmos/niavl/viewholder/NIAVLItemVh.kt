package com.harismexis.cosmos.niavl.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.cosmos.databinding.VhNiavlItemBinding
import com.harismexis.cosmos.niavl.model.remote.NIAVLDataEntry
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
        itemView.context.populateWithGlide(binding.imgv, item.thumbUrl)
        setupPlayIcon(item.mediaType == NIAVLDataEntry.MEDIA_TYPE_VIDEO)
        binding.txt1.text = item.description
        binding.root.setOnClickListener {
            itemClickListener.onNIAVLItemClick(item, position)
        }
    }

    private fun setupPlayIcon(show: Boolean) {
        if (show) {
            binding.playIcon.visibility = View.VISIBLE
        } else {
            binding.playIcon.visibility = View.GONE
        }
    }

    fun unbind() {}
}