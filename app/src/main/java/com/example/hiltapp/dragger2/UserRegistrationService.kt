package com.example.hiltapp.dragger2

import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    private val userRepo: UserRepo,
    private val emailService: EmailService
){
    fun register(email: String, password: String){
        userRepo.saveUser(email, password)
        emailService.send(email, "test@gmail.com", "Hello World")
    }
}