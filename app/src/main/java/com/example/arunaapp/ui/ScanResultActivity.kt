package com.example.arunaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arunaapp.MainActivity
import com.example.arunaapp.databinding.ActivityScanResultBinding

class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}