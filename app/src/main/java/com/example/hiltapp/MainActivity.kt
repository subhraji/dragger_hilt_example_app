package com.example.hiltapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hiltapp.databinding.ActivityMainBinding
import com.example.hiltapp.repository.Outcome
import com.example.hiltapp.viewmodels.GetTodosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var mainViewModel: GetTodosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(GetTodosViewModel::class.java)

        mainViewModel.todosLiveData.observe(this, Observer { outcome ->
            when(outcome){
                is Outcome.Success ->{
                    if(outcome.data.size > 0){
                        binding.msgTv.text = outcome.data[0].title.toString()
                    }else{
                        Toast.makeText(this,"size => "+outcome.data.size, Toast.LENGTH_SHORT).show()
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