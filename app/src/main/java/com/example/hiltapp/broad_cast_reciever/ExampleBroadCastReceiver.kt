package com.example.hiltapp.broad_cast_reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class ExampleBroadCastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if("com.example.test".equals(p1?.action)){
            val receiverText = p1?.getStringExtra("com.example.test.data")
            Toast.makeText(p0, receiverText?:"not found", Toast.LENGTH_LONG).show()
        }
    }
}


/*
explicit broadcast receiver can be used to receive our own components (custom components) apart from
system components.
*/