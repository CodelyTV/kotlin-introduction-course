package com.codely.demo

import java.time.LocalDate

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    val date = LocalDate.parse(readLine())
    println("You wrote $date")
}
