package com.example.hiltapp.coroutine_flow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiltapp.R
import com.example.hiltapp.databinding.ActivityDataBindingTestBinding
import com.example.hiltapp.databinding.ActivityLearnFlowBinding
import com.example.hiltapp.model.GetTodosResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LearnFlowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLearnFlowBinding
    private var TAG = "LearnFlowActivity"
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLearnFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
                result.collect {
                    Log.e(TAG, "1st consumer => ${it}")
                }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2500)
            result.collect {
                Log.e(TAG, "2nd consumer => ${it}")
            }
        }*/
        //consumeStateFlow()


        //broadcast receiver
        val _intent: Intent = intent
        val action = _intent.action
        val type = _intent.type
        if(Intent.ACTION_SEND.equals(action) && type != null){
            binding.image.setImageURI(_intent.getParcelableExtra(Intent.EXTRA_STREAM))
        }

    }

    // example of flow
    /*fun producer() : Flow<Int> {
        return flow {
            withContext(Dispatchers.IO){
                val list = listOf<Int>(1,2,3,4,5,6,7,8)
                list.forEach {
                    delay(1000)
                    emit(it)
                }
            }
        }
    }*/

    // example of sharedflow
    /*fun producer() : SharedFlow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>()
        val list = listOf<Int>(1,2,3,4,5,6)
        GlobalScope.launch {
            list.forEach{
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }*/

    // example of stateFlow

    private fun consumeStateFlow(){
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(6000)
            result.collect {
                Log.e(TAG, "consumer => ${it}")
            }
        }
    }
    fun producer() : StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }
}

/*
Flow has two type of operator:
    1.Terminal operator:

           terminal operators starts the flow, all the terminal operators are suspend fun.
           e.g: .collect{}, .first(), .toList()


    2.Non Terminal operator:
            e.g: .map{}, .filter{}, .buffer{}

    flowon(Dispathers.IO), flowon(Dispathers.Main) etc is used for context swicthing
    all the operators above flowon(Dispathers.IO) runs on IO thread

    e.g:
                   producer()
                    .map {
                        it * 2
                    }
                    .flowOn(Dispatchers.IO)
                    .collect {
                        binding.dataTv.text = it.toString()
                    }


     exception handle on flow .catch{} operator on producer

     flow is cold in nature:
        if there is no consumer to collect data then the flow producer does not produces or emits data.

     shared flow is hot in nature i.e sharedFlow is a hot flow
        in shared flow multiple consumers get same data if a consumer starts consuming tha data a bit late then that
        consumer will get the data from that state. from 1 - 2 - 3 - 4 -5 in this case the late comer consumer will get
        the data from 3 to 5 if the consumer joins from 3


      replay() in shared flow is used for buffer to store old value


      StateFlow is hot Flow kind of shared flow, Multiple conumer can listen to a state flow but the state flow always
      hold or maintains the last/latest value.


      //////////////// Livedata  vs   StateFlow //////////////////

      1. live data is life cycle dependent but stateflow is coroutine scope dependent. e.g can not use live date in repo
             as repository does not has any lifecycle.
      2. Live data operator runs on main thread but stateflow runs on coroutine scope and can be easily switch scope.

      3. Live data has less number of operator as compared to stateflow or flow.
*/
