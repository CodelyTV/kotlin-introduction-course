package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    val line = supportNullableString(readLine())
    if (!line.isNullOrEmpty()) {
        val date = LocalDate.parse(line)
        if (date == null){
            println("The introduced date <$date> is not valid")
            exitProcess(1)
        } else {
            println("You wrote the date $date")
            val currentDate = LocalDate.now()
            val difference = Period.between(date, currentDate).years
            println("The difference between the date you wrote an today is $difference years")
        }
    } else {
        println("The introduced date <$line> is not valid")
        exitProcess(1)
    }

    println("Bye!")
}

private fun supportNullableString(line: String?) = line
