package com.example.cosmos.mrp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmos.databinding.VhMrpItemBinding
import com.example.cosmos.mrp.model.response.MRPItem
import com.example.cosmos.mrp.viewholder.MRPItemVh

class MRPItemAdapter(
    private var models: List<MRPItem>,
    private var itemClickListener: MRPItemVh.MRPItemClickListener
) : RecyclerView.Adapter<MRPItemVh>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MRPItemVh {
        return MRPItemVh(
            VhMrpItemBinding.inflate(LayoutInflater.from(parent.context)),
            itemClickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: MRPItemVh,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onViewDetachedFromWindow(holder: MRPItemVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}