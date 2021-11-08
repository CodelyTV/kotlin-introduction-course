package com.codely.demo

class App() {
    fun execute() {
        println("=======>START KOTLIN CODE<=======")
        val list = listOf("siames", "azul ruso", "comun europeo")
        val appJava = AppJava()
        println(list.map { appJava.toUpperCaseBreed(it) })
        println(list.filter {
            it.contains("europeo")
        })
        println("=======>END KOTLIN CODE<=======")
    }
    fun toLowerCaseBreed(breed: String): String = breed.lowercase()
}
