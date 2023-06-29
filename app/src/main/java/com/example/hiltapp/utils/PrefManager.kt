package com.example.hiltapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.hiltapp.MyApplication

object PrefManager {
    private val KEY_USER_MODEL: String?="MODEL"
    private var sharedPreferences: SharedPreferences = MyApplication.application.applicationContext.getSharedPreferences(
        "",
        Context.MODE_PRIVATE
    )

    // Keys
    const val KEY_AUTH_TOKEN = "Auth_Token"
    const val APP_LANGUAGE = "app_lang"

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getInt(key: String): Int? {
        return sharedPreferences.getInt(key,0)
    }

    fun putString(key: String, value: String?) {
        var mEditor: SharedPreferences.Editor
        mEditor = sharedPreferences.edit()
        mEditor.putString(key, value)
        mEditor.commit()
    }

    fun putInt(key: String, value: Int?) {
        //value?.let { sharedPreferences.edit().putInt(key, it).apply() }
        var mEditor: SharedPreferences.Editor
        mEditor = sharedPreferences.edit()
        value?.let {
            mEditor.putInt(key, it)
            mEditor.commit()
        }

    }

    fun putBoolean(key: String, value: Boolean?) {
        //value?.let { sharedPreferences.edit().putBoolean(key, it).apply() }
        var mEditor: SharedPreferences.Editor
        mEditor = sharedPreferences.edit()
        value?.let { mEditor.putBoolean(key, it) }
        mEditor.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    /*fun clearPref(){
        val editor = sharedPreferences.edit()
        editor.remove(IS_LOGIN)
        editor.remove(KEY_AUTH_TOKEN)
        editor.apply()
    }*/


    fun getKeyAuthToken(): String? {
        return getString(KEY_AUTH_TOKEN)
    }

    fun setKeyAuthToken(auth_token: String) {
        putString(KEY_AUTH_TOKEN, auth_token)
    }

    fun getLang(): String? {
        return getString(APP_LANGUAGE)
    }

    fun setLang(app_lang: String) {
        putString(APP_LANGUAGE, app_lang)
    }
}