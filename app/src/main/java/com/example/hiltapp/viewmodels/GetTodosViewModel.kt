package com.example.hiltapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltapp.model.GetTodosResponse
import com.example.hiltapp.repository.GetTodosRepository
import com.example.hiltapp.repository.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTodosViewModel @Inject constructor(private val repository: GetTodosRepository) : ViewModel() {

    val todosLiveData : LiveData<Outcome<GetTodosResponse>>
    get() = repository.todos

    init {
        viewModelScope.launch {
            repository.getTodos()
        }
    }

}
