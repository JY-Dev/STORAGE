package com.example.storage.ui.main

import android.os.Bundle
import com.example.storage.R
import com.example.storage.databinding.ActivityMainBinding
import com.example.storage.base.BaseActivity

class MainActivity : BaseActivity() {
    val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity

        }

    }
}