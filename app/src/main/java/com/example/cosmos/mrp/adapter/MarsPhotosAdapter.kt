package com.example.cosmos.mrp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmos.R
import com.example.cosmos.mrp.model.response.MarsPhoto
import com.example.cosmos.mrp.viewholder.MRPVh

class MarsPhotosAdapter(
    private var models: List<MarsPhoto>,
    // private var itemClickListener: ResponderViewHolder.ItemClickListener
) : RecyclerView.Adapter<MRPVh>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MRPVh {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mars_photo, parent, false)
        return MRPVh(view)
    }

    override fun onBindViewHolder(
        viewHolder: MRPVh,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onViewDetachedFromWindow(holder: MRPVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

//    fun notifyDataChanged() {
//        notifyItemRangeChanged(1, itemCount - 1)
//    }

//    override fun getItemId(position: Int): Long {
//        return models[position].currencyCode.id
//    }

}