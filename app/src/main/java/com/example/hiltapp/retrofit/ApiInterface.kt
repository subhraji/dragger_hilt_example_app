package com.example.hiltapp.retrofit

import com.example.hiltapp.model.GetTodosResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("todos")
    suspend fun getTodos(): GetTodosResponse?
}