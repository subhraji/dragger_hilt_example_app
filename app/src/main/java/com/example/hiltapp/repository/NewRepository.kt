package com.example.hiltapp.repository

import com.example.hiltapp.model.GetTodosResponse
import com.example.hiltapp.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewRepository @Inject constructor(private val apiInterface: ApiInterface)  {
    fun getTodos(): Flow<GetTodosResponse?> = flow{
        emit(apiInterface.getTodos())
    }.flowOn(Dispatchers.IO)
}