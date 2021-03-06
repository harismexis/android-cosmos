package com.harismexis.cosmos.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.home.viewholder.factory.createHomeViewHolder
import com.harismexis.cosmos.workshared.enums.RowType

class HomeAdapter(
    private var models: List<RowType>,
    private var clickListener: HomeClickListener
) : RecyclerView.Adapter<BaseHomeVh>() {

    companion object {
        const val VIEW_TYPE_APOND = 0
        const val VIEW_TYPE_MRP = 1
        const val VIEW_TYPE_NIAVL = 2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHomeVh {
        return createHomeViewHolder(viewType, LayoutInflater.from(parent.context), clickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_APOND
            1 -> VIEW_TYPE_MRP
            else -> VIEW_TYPE_NIAVL
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

}