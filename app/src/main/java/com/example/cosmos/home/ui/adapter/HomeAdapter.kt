package com.example.cosmos.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmos.home.ui.viewholder.BaseHomeVh
import com.example.cosmos.home.ui.viewholder.factory.createHomeViewHolder
import com.example.cosmos.mrp.model.response.MarsPhoto

class HomeAdapter(
    private var models: List<MarsPhoto>
) : RecyclerView.Adapter<BaseHomeVh>() {

    companion object {
        const val VIEW_TYPE_APOND = 0
        const val VIEW_TYPE_MRP = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHomeVh {
        return createHomeViewHolder(viewType, LayoutInflater.from(parent.context))
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_APOND
            else -> VIEW_TYPE_MRP
        }
    }

    override fun onBindViewHolder(
        viewHolder: BaseHomeVh,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onViewDetachedFromWindow(holder: BaseHomeVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

//    override fun getItemId(position: Int): Long {
//        return models[position].currencyCode.id
//    }

}