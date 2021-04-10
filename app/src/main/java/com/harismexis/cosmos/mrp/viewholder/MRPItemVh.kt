package com.harismexis.cosmos.mrp.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.VhMrpItemBinding
import com.harismexis.cosmos.mrp.model.ui.MRPUiModel
import com.harismexis.cosmos.workshared.extensions.populateWithGlide

class MRPItemVh(
    private var binding: VhMrpItemBinding,
    private var itemClickListener: MRPItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface MRPItemClickListener {
        fun onMRPItemClick(item: MRPUiModel, position: Int)
    }

    fun bind(
        item: MRPUiModel,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgPhoto, item.imgSrc)
        binding.txtDate.text = item.earthDate.toString()
        binding.txtID.text = itemView.context.getString(R.string.vh_mrp_item_id_txt, item.id)
        binding.root.setOnClickListener {
            itemClickListener.onMRPItemClick(item, position)
        }
    }

    fun unbind() {}
}