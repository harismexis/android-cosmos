package com.example.cosmos.marsroverphotos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmos.R
import com.example.cosmos.marsroverphotos.model.response.MarsPhoto

class MarsPhotosAdapter(
    private var models: List<MarsPhoto>,
    // private var itemClickListener: ResponderViewHolder.ItemClickListener
) : RecyclerView.Adapter<MarsPhotoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotoViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mars_photo, parent, false)
        return MarsPhotoViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: MarsPhotoViewHolder,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onViewDetachedFromWindow(holder: MarsPhotoViewHolder) {
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