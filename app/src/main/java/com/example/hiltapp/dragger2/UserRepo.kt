package com.example.hiltapp.dragger2

import android.util.Log
import javax.inject.Inject

class UserRepo @Inject constructor(){
    fun saveUser(email: String, password: String){
        Log.d("dagger", "$email and $password")
    }
}