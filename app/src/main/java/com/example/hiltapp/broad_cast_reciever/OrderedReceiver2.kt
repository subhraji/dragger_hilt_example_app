package com.example.hiltapp.broad_cast_reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OrderedReceiver2: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "OR2 Received", Toast.LENGTH_SHORT).show()
    }
}