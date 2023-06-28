package com.example.hiltapp.viewmodels

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltapp.model.GetTodosResponse
import com.example.hiltapp.repository.GetTodosRepository
import com.example.hiltapp.repository.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChangeLangViewModel @Inject constructor(application: Application) : ViewModel() {

    init {
        viewModelScope.launch {
            /*val preferences = application.getSharedPreferences("Settings", AppCompatActivity.MODE_PRIVATE)
            val language: String = preferences.getString("app_lang","").toString()
            setLocal(application, language)*/
        }
    }

    /*fun logout(
        token: String,
    ): Flow<String?> = flow{
        emit(apiInterface.logout(token))
    }.flowOn(Dispatchers.IO)*/

    fun setLocal(application: Application ,language: String){
        var local: Locale = Locale(language)
        Locale.setDefault(local)
        var configuration: Configuration = Configuration()
        configuration.setLocale(local)
        application.resources.updateConfiguration(configuration, application.resources.displayMetrics)
        //application.createConfigurationContext(configuration)

    }
}
