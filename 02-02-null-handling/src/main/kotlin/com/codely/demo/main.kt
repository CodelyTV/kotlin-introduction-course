package com.codely.demo

import java.time.LocalDate

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    supportNullableString(readLine())?.let {
        val date = LocalDate.parse(it)
        println("You wrote the date $date")
    }
    println("Bye!")
}

private fun supportNullableString(line: String?) = line
private fun forceNotNullByThrowingNullPointerException(line: String?) = line!!
private fun forceNotNullByThrowingSpecifiedException(line: String?) = line ?: throw RuntimeException("The date cannot be null")
