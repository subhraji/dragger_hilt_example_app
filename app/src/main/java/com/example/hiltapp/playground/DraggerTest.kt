package com.example.hiltapp.playground

class DraggerTest {

}

fun main() {
    val engine: Engine = Engine()
    Car(engine).drive()
}

class Engine{
    fun start(){
        println("engine started...")
    }
}

class Car(private val engine: Engine){

    fun drive(){
        engine.start()
    }
}