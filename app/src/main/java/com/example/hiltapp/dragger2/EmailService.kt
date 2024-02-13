package com.example.hiltapp.dragger2

import android.util.Log
import javax.inject.Inject

class EmailService @Inject constructor(){
    fun send(to: String, from: String, body: String){
        Log.d("dagger", "email sent to $to from $from $body")
    }
}