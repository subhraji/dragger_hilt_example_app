package com.example.hiltapp.test_module

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Priyanka",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Rahul",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password repeated incorrectly returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Rahul",
            "123",
            "122"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than two digit returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Rahul",
            "2",
            "2"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `string contains correct braces returns true`(){
        val result = RegistrationUtil.checkBraces("()")
        assertThat(result).isTrue()
    }

    @Test
    fun `string contains reverse braces returns false`(){
        val result = RegistrationUtil.checkBraces(")(")
        assertThat(result).isTrue()
    }

    @Test
    fun `string contains same braces returns false`(){
        val result = RegistrationUtil.checkBraces("(()")
        assertThat(result).isFalse()
    }
}