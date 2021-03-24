package com.harismexis.cosmos.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.harismexis.cosmos.workshared.enums.RowType
import com.harismexis.cosmos.home.interfaces.HomeClickListener

abstract class BaseHomeVh(
    private var binding: ViewBinding,
    private var click: HomeClickListener
) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(
        rowType: RowType,
        position: Int
    ) {
        binding.root.setOnClickListener {
            click.onHomeItemClick(rowType, position)
        }
    }

    open fun unbind() {

    }

}
