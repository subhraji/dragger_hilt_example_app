package com.example.hiltapp.test_module

object RegistrationUtil {

    private val existingUsers = listOf<String>("Peter","Carl")
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        if(username.isEmpty() || password.isEmpty()){
            return false
        }
        if (username in  existingUsers){
            return false
        }
        if(password != confirmPassword){
            return false
        }
        if (password.count{ it.isDigit() } < 2){
            return false
        }
        return true 
    }

    fun checkBraces(string: String): Boolean{
        return string.count {it == '('} == string.count { it == ')' }
    }
}