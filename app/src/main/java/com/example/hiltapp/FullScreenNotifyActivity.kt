package com.example.hiltapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.hiltapp.databinding.ActivityFullScreenNotifyBinding
import com.example.hiltapp.databinding.ActivityMainBinding
import com.example.hiltapp.repository.Outcome
import com.example.hiltapp.viewmodels.NewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullScreenNotifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenNotifyBinding
    private val mainViewModel: NewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flags: Int = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.addFlags(flags)

        binding= ActivityFullScreenNotifyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.clickBtn.setOnClickListener {
            mainViewModel.response.observe(this, Observer { outcome ->
                when(outcome){
                    is Outcome.Success ->{
                        if(outcome.data?.size!! > 0){
                            binding.titleTv.text = outcome.data!![0].title.toString()
                        }else{
                            Toast.makeText(this,"size => "+ outcome.data!!.size, Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Outcome.Failure<*> -> {
                        Toast.makeText(this,outcome.e.message, Toast.LENGTH_SHORT).show()

                        outcome.e.printStackTrace()
                        Log.i("status",outcome.e.cause.toString())
                    }
                }
            })
        }

    }
}