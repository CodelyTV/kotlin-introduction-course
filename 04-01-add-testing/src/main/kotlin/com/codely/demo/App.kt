package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

open class App {
    fun execute(line: String?) {
        line.takeUnless {
            !it.isNullOrEmpty() && !it.isNullOrBlank()
        }?.let {
            show("The introduced date <$it> is not valid")
            return
        }
        line.takeUnless {
                it.isNullOrEmpty()
            }?.let {
                LocalDate.parse(it)
            }.apply {
                if (this == null) {
                    show("The introduced date <$this> is not valid")
                    exitProcess(1)
                }
            }.also {
                show("You wrote the date $it")
            }.takeIf {
                it != null
            }?.run {
                this.calculateDifferenceUntilToday()
            }

        show("Bye!")
    }

    protected open fun currentDate(): LocalDate = LocalDate.now()
    protected open fun show(message: String) {
        println(message)
    }
    private fun LocalDate.calculateDifferenceUntilToday() = kotlin.with(Period.between(this, currentDate())) {
        when {
            years > 0 -> show("The difference between the date you wrote an today is $years years")
            months > 0 -> show("The difference between the date you wrote an today is $months months")
            days > 0 -> show("The difference between the date you wrote an today is $days days")
        }
    }
}

fun main() {
    println("Please enter a date with the format <yyyy-MM-dd>")
    App().execute(supportNullableString(readLine()))
}

private fun supportNullableString(line: String?) = line
