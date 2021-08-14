package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

class App

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    val date = supportNullableString(readLine())
    .takeUnless {
        it.isNullOrEmpty()
    }?.let {
        LocalDate.parse(it)
    }.apply {
        if (this == null) {
            println("The introduced date <$this> is not valid")
            exitProcess(1)
        }
    }.also {
        println("You wrote the date $it")
    }
    val difference = date.takeIf {
            date != null
    }?.run {
        val currentDate = LocalDate.now()
        Period.between(this, currentDate).years
    }

    with(difference) {
        println("The difference between the date you wrote an today is $this years")
    }

    println("Bye!")
}

private fun supportNullableString(line: String?) = line
