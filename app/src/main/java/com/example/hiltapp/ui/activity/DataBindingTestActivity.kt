package com.example.hiltapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hiltapp.R
import com.example.hiltapp.databinding.ActivityDataBindingTestBinding
import com.example.hiltapp.databinding.ActivityMainBinding

class DataBindingTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDataBindingTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}