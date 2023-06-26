package com.example.hiltapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hiltapp.databinding.ActivityMainBinding
import com.example.hiltapp.repository.Outcome
import com.example.hiltapp.viewmodels.GetTodosViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var mainViewModel: GetTodosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(GetTodosViewModel::class.java)

        loadLocal()
        binding.changeLangBtn.setOnClickListener {
            changeLanguage()
        }

        //observe
        //observeApiCall()
    }

    private fun changeLanguage(){
        // initialise the list items for the alert dialog
        val listItems = arrayOf("English", "Assamese")
        val checkedItems = BooleanArray(listItems.size)

        // copy the items from the main list to the selected item list for the preview
        // if the item is checked then only the item should be displayed for the user
        val selectedItems = mutableListOf(*listItems)

        // initialise the alert dialog builder
        val builder = AlertDialog.Builder(this)

        // set the title for the alert dialog
        builder.setTitle("Choose Language")

        // set the icon for the alert dialog
        builder.setIcon(R.drawable.ic_launcher_foreground)

        // now this is the function which sets the alert dialog for multiple item selection ready
        builder.setMultiChoiceItems(listItems, checkedItems) { dialog, which, isChecked ->
            checkedItems[which] = isChecked
            val currentItem = selectedItems[which]

            if(which == 0){
                setLocal("en")
                recreate()
            }else if(which == 1){
                setLocal("as")
                recreate()
            }
        }

        builder.setCancelable(false)
        builder.setNegativeButton("CANCEL") { dialog, which -> }
        builder.create()
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun setLocal(language: String){
        var local: Locale = Locale(language)
        Locale.setDefault(local)
        var configuration: Configuration = Configuration()
        configuration.setLocale(local)
        baseContext.resources.updateConfiguration(configuration, baseContext.resources.displayMetrics)

        val sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("app_lang", language)
        myEdit.apply()
    }

    private fun loadLocal(){
        val preferences = getSharedPreferences("Settings", MODE_PRIVATE)
        val language: String = preferences.getString("app_lang","").toString()
        setLocal(language)
    }

    private fun observeApiCall(){
        mainViewModel.todosLiveData.observe(this, Observer { outcome ->
            when(outcome){
                is Outcome.Success ->{
                    if(outcome.data.size > 0){
                        binding.msgTv.text = outcome.data[0].title.toString()
                    }else{
                        Toast.makeText(this,"size => "+outcome.data.size, Toast.LENGTH_SHORT).show()
                    }
                }

                is Outcome.Failure<*> -> {
                    Toast.makeText(this,outcome.e.message, Toast.LENGTH_SHORT).show()

                    outcome.e.printStackTrace()
                    Log.i("status",outcome.e.cause.toString())
                }
            }
        })
    }
}