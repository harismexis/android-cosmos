package com.example.cosmos.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cosmos.home.interfaces.HomeClickListener
import com.example.cosmos.workshared.enums.RowType

abstract class BaseHomeVh(
    private var viewBinding: ViewBinding,
    private var click: HomeClickListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    open fun bind(
        rowType: RowType,
        position: Int
    ) {
        viewBinding.root.setOnClickListener {
            click.onHomeItemClick(rowType, position)
        }
    }

    open fun unbind() {

    }

}
