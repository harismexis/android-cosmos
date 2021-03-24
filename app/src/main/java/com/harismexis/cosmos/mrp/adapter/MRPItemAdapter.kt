package com.harismexis.cosmos.mrp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.cosmos.databinding.VhMrpItemBinding
import com.harismexis.cosmos.mrp.model.ui.MRPItemModel
import com.harismexis.cosmos.mrp.viewholder.MRPItemVh

class MRPItemAdapter(
    private var models: List<MRPItemModel>,
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

    override fun getItemId(position: Int): Long {
        return models[position].id.toLong()
    }

    override fun onViewDetachedFromWindow(holder: MRPItemVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}