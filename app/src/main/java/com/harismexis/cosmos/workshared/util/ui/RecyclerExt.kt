package com.harismexis.cosmos.workshared.util.ui

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setDivider(
    @DrawableRes divider: Int
){
    ContextCompat.getDrawable(this.context, divider)?.let {
        this.addItemDecoration(RecyclerDivider(it))
    }
}