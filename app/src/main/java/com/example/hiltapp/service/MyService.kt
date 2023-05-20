package com.example.hiltapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.concurrent.timerTask


class MyService : Service() {
    var counter = 0
    private val timer: Timer? = Timer()
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        doSomethingRepeatedly()
        return START_STICKY
    }

    private fun doSomethingRepeatedly() {
        timer?.scheduleAtFixedRate(timerTask {
            Log.e("NIlu_TAG","${counter++} Hello World")
        },0,10000)
        /*timer.scheduleAtFixedRate(object : TimerTask() {
            fun run() {
                Log.d("MyService", "${counter+1.toString()}")
            }
        }, 0, UPDATE_INTERVAL)*/
    }

    override fun onDestroy() {
        super.onDestroy()

        timer?.let {
            it.cancel()
        }
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val UPDATE_INTERVAL = 1000
    }
}