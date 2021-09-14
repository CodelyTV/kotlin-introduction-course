package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    val date = supportNullableString(readLine())
        .takeUnless {
            it.isNullOrEmpty() || it.isNullOrBlank()
        }?.let {
            LocalDate.parse(it)
        }

        if (date == null) {
            println("The introduced date <$date> is not valid")
            exitProcess(1)
        } else {
            date.also {
                println("You wrote the date $it")
            }
            val currentDate = LocalDate.now()
            with(Period.between(date, currentDate).years) {
                println("The difference between the date you wrote an today is $this years")
            }

        }

    println("Bye!")
}

private fun supportNullableString(line: String?) = line
