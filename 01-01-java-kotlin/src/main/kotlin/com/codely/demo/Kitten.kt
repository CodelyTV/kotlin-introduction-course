package com.codely.demo

data class Kitten(val name: String)

fun main() {
    println(Kitten("Mandarina"))
    println(Kitten("Mandarina") == Kitten("Mandarina"))
    println(KittenJava("Mandarina"))
    println(KittenJava("Mandarina") == KittenJava("Mandarina"))
}
