package com.harismexis.cosmos.mrp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.harismexis.cosmos.databinding.ActivityMrpDetailBinding
import com.harismexis.cosmos.workshared.activity.BaseActivity

class MRPDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityMrpDetailBinding

    companion object {
        fun Context.startMRPDetailActivity() {
            this.startActivity(Intent(this, MRPDetailActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initialiseViewBinding() {
        binding = ActivityMrpDetailBinding.inflate(layoutInflater)
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun setupActionBar() {
        // do nothing
    }
}