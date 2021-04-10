package com.harismexis.cosmos.mrp.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.VhMrpItemBinding
import com.harismexis.cosmos.mrp.model.ui.MRPUiModel

class MRPItemVh(
    private var binding: VhMrpItemBinding,
    private var itemClickListener: MRPItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val tag = MRPItemVh::class.qualifiedName

    interface MRPItemClickListener {
        fun onMRPItemClick(item: MRPUiModel, position: Int)
    }

    fun bind(
        item: MRPUiModel,
        position: Int
    ) {
        populateImage(item.imgSrc)
        binding.txtDate.text = item.earthDate.toString()
        binding.txtID.text = itemView.context.getString(R.string.vh_mrp_item_id_txt, item.id)
        binding.root.setOnClickListener {
            itemClickListener.onMRPItemClick(item, position)
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