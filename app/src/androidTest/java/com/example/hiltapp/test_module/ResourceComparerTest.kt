package com.example.hiltapp.test_module

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.hiltapp.R
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResourceComparerTest{
    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setup(){
        resourceComparer  = ResourceComparer()
    }

    @Test
    fun stringRes_sameAsGivenString_retunsTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "Hilt App")

        assertThat(result).isTrue()
    }

    @Test
    fun stringRes_notSameAsGivenString_retunsFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "omron App")

        assertThat(result).isFalse()
    }
}