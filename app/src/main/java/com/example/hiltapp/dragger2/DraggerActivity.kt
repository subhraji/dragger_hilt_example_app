package com.example.hiltapp.dragger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hiltapp.R
import com.example.hiltapp.databinding.ActivityDraggerBinding

class DraggerActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityDraggerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDraggerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val component = DaggerUserRegistrationComponent.builder().build()
        component.getUserRegistrationService().register("papai@gmail.com", "12345")
    }
}