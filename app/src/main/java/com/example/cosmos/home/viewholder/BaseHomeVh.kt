package com.example.cosmos.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cosmos.home.interfaces.HomeClickListener

abstract class BaseHomeVh(
    private var viewBinding: ViewBinding,
    private var click: HomeClickListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    open fun bind(
        item: Any?,
        position: Int
    ) {
        viewBinding.root.setOnClickListener {
            click.onHomeItemClick(it, position)
        }
    }

    open fun unbind() {

    }

}
