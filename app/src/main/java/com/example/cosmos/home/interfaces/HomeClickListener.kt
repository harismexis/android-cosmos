package com.example.cosmos.home.interfaces

import com.example.cosmos.workshared.enums.RowType

interface HomeClickListener {
    fun onHomeItemClick(rowType: RowType, position: Int)
}