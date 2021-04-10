package com.harismexis.cosmos.niavl.viewholder

import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.VhHomeSimpleRowBinding
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewholder.BaseHomeVh
import com.harismexis.cosmos.workshared.enums.RowType

class NIAVLHomeVh(
    private var viewBinding: VhHomeSimpleRowBinding,
    rowClickListener: HomeClickListener
) : BaseHomeVh(viewBinding, rowClickListener) {

    private val tag = NIAVLHomeVh::class.qualifiedName

    override fun bind(
        rowType: RowType,
        position: Int
    ) {
        super.bind(rowType, position)
        updateUI()
    }

    private fun updateUI() {
        viewBinding.apply {
            txtTitle.text = itemView.context.getString(R.string.vh_home_niavl_title)
            txtMeta.text = itemView.context.getString(R.string.vh_home_niavl_meta)
        }
    }

}