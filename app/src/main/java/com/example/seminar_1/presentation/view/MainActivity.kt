package com.example.seminar_1.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminar_1.R
import com.example.seminar_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            
        }
    }
}