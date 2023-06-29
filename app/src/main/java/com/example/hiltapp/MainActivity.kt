package com.example.hiltapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hiltapp.databinding.ActivityMainBinding
import com.example.hiltapp.repository.Outcome
import com.example.hiltapp.ui.activity.DataBindingTestActivity
import com.example.hiltapp.utils.LocaleHelper
import com.example.hiltapp.utils.PrefManager
import com.example.hiltapp.viewmodels.ChangeLangViewModel
import com.example.hiltapp.viewmodels.GetTodosViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: GetTodosViewModel by viewModels()
    private val changeLangViewModel: ChangeLangViewModel by viewModels()
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadLocal()
        binding.changeLangBtn.setOnClickListener {
            changeLanguage()
        }

        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, DataBindingTestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun changeLanguage(){
        val listItems = arrayOf("English", "Assamese")
        val checkedItems = BooleanArray(listItems.size)
        val selectedItems = mutableListOf(*listItems)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Language")
        builder.setIcon(R.drawable.ic_launcher_foreground)

        builder.setMultiChoiceItems(listItems, checkedItems) { dialog, which, isChecked ->
            checkedItems[which] = isChecked
            val currentItem = selectedItems[which]
            if(which == 0){
                //setLocal("en")
                context = LocaleHelper.setLocale(this, "en")
                initUi()
                //recreate()
                dialog.dismiss()
            }else if(which == 1){
                //setLocal("as")
                context = LocaleHelper.setLocale(this, "as")
                initUi()
                //recreate()
                dialog.dismiss()
            }
        }

        builder.setCancelable(false)
        builder.setNegativeButton("CANCEL") { dialog, which -> }
        builder.create()
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun loadLocal(){
        PrefManager.getLang()?.let { LocaleHelper.setLocale(this, it) }
        initUi()
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

    private fun initUi(){
        binding.msgTv.text = resources.getString(R.string.hello_world)
        binding.newMsgTv.text = resources.getString(R.string.how_are_you)
        binding.nextBtn.text = resources.getString(R.string.next)
    }

}