package com.example.cosmos.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.workshared.enums.RowType

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
