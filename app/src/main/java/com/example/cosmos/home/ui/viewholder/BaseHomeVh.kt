package com.example.cosmos.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseHomeVh(
    viewBinding: ViewBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun bind(
        item: Any?,
        position: Int
    )

    abstract fun unbind()
}