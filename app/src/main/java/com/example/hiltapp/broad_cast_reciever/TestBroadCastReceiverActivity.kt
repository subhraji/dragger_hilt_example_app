package com.example.hiltapp.broad_cast_reciever

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.hiltapp.R
import com.example.hiltapp.databinding.ActivityMainBinding
import com.example.hiltapp.databinding.ActivityTestBroadCastReceiverBinding

class TestBroadCastReceiverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBroadCastReceiverBinding

    val orderedReceiver1: OrderedReceiver1 = OrderedReceiver1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTestBroadCastReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filter = IntentFilter("com.example.test")
        filter.priority = 1
        registerReceiver(orderedReceiver1, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(orderedReceiver1)
    }
}