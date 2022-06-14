package com.example.hiltapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hiltapp.model.GetTodosResponse
import com.example.hiltapp.retrofit.ApiInterface
import javax.inject.Inject

class GetTodosRepository @Inject constructor(private val apiInterface: ApiInterface) {

    private val _todos = MutableLiveData<Outcome<GetTodosResponse>>()
    val todos: LiveData<Outcome<GetTodosResponse>>
        get() = _todos

    suspend fun getTodos(){
        try {
            val response = apiInterface.getTodos()
            _todos.postValue(Outcome.success(response!!))
        } catch (e: Throwable) {
            _todos.postValue(Outcome.failure(e))
        }
    }

}