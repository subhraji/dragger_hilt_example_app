package com.example.hiltapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltapp.model.GetTodosResponse
import com.example.hiltapp.repository.NewRepository
import com.example.hiltapp.repository.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewViewModel @Inject constructor(private val repository: NewRepository) : ViewModel() {
    private var _response = MutableLiveData<Outcome<GetTodosResponse?>>()
    val response: LiveData<Outcome<GetTodosResponse?>> = _response

    init {
        getTodos()
    }

    fun getTodos() = viewModelScope.launch {
        repository.getTodos().onStart {
            _response.value = Outcome.loading(true)
        }.catch {
            _response.value = Outcome.Failure(it)
        }.collect {
            _response.value = Outcome.success(it)
        }
    }
}