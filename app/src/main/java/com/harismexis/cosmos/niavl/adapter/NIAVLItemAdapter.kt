package com.harismexis.cosmos.niavl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.cosmos.databinding.VhNiavlItemBinding
import com.harismexis.cosmos.niavl.model.ui.NIAVLUiModel
import com.harismexis.cosmos.niavl.viewholder.NIAVLItemVh

class NIAVLItemAdapter(
    private var models: List<NIAVLUiModel>,
    private var itemClickListener: NIAVLItemVh.NIAVLItemClickListener
) : RecyclerView.Adapter<NIAVLItemVh>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NIAVLItemVh {
        return NIAVLItemVh(
            VhNiavlItemBinding.inflate(LayoutInflater.from(parent.context)),
            itemClickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: NIAVLItemVh,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

//    override fun getItemId(position: Int): Long {
//        return models[position].id.toLong()
//    }

    override fun onViewDetachedFromWindow(holder: NIAVLItemVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}