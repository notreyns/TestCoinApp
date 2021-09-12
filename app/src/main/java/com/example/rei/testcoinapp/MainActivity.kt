package com.example.rei.testcoinapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.rei.testcoinapp.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initializeNavController()
    }
}