package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    supportNullableString(readLine())
    .takeUnless {
        it.isNullOrEmpty() || it.isNullOrBlank()
    }?.let {
        LocalDate.parse(it)
    }.apply {
        if (this == null) {
            println("The introduced date <$this> is not valid")
            exitProcess(1)
        }
    }.also {
        println("You wrote the date $it")
    }.takeIf {
            it != null
    }?.run {
        val currentDate = LocalDate.now()
        with(Period.between(this, currentDate).years) {
            println("The difference between the date you wrote an today is $this years")
        }
    }

    println("Bye!")
}

private fun supportNullableString(line: String?) = line
