package com.example.cosmos.mrp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cosmos.R

fun Context.startMRPDetailActivity() {
    this.startActivity(Intent(this, MRPDetailActivity::class.java))
}

class MRPDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mrp_detail)
    }
}