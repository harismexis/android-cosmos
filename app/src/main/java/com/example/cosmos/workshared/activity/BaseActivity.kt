package com.example.cosmos.workshared.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(getRootView())
        initialiseView()
        initialiseViewModel()
    }

    abstract fun initialiseViewBinding()

    abstract fun getRootView(): View

    open fun initialiseView() {
        setupActionBar()
    }

    abstract fun initialiseViewModel()

    open fun setupActionBar() {
        setSupportActionBar(getToolbar())
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    abstract fun getToolbar(): Toolbar

}