package com.example.hiltapp.model

data class GetTodosResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)