package com.harismexis.cosmos.home.interfaces

import com.harismexis.cosmos.workshared.enums.RowType

interface HomeClickListener {
    fun onHomeItemClick(rowType: RowType, position: Int)
}