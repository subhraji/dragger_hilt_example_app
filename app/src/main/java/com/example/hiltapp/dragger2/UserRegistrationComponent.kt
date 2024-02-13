package com.example.hiltapp.dragger2

import dagger.Component

@Component
interface UserRegistrationComponent {
    fun getUserRegistrationService() : UserRegistrationService
    fun getEmailService() : EmailService

}