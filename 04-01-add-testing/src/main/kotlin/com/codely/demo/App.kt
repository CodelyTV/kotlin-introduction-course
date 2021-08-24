package com.codely.demo

import java.time.LocalDate
import java.time.Period
import kotlin.system.exitProcess

open class Reader {
    open fun read() = readLine()
}

open class Writer {
    open fun write(message: String) = println(message)
}
open class App(private val reader: Reader, private val writer: Writer) {
    fun execute() {
        writer.write("Please enter a date with the format <yyyy-MM-dd>")
        val line = reader.read()
        line.takeUnless {
            !it.isNullOrEmpty() && !it.isNullOrBlank()
        }?.let {
            writer.write("The introduced date <$it> is not valid")
            return
        }
        line.takeUnless {
                it.isNullOrEmpty()
            }?.let {
                LocalDate.parse(it)
            }.apply {
                if (this == null) {
                    writer.write("The introduced date <$this> is not valid")
                    exitProcess(1)
                }
            }.also {
                writer.write("You wrote the date $it")
            }.takeIf {
                it != null
            }?.run {
                this.calculateDifferenceUntilToday()
            }

        writer.write("Bye!")
    }

    protected open fun currentDate(): LocalDate = LocalDate.now()
    private fun LocalDate.calculateDifferenceUntilToday() = kotlin.with(Period.between(this, currentDate())) {
        when {
            years > 0 -> writer.write("The difference between the date you wrote an today is $years years")
            months > 0 -> writer.write("The difference between the date you wrote an today is $months months")
            days > 0 -> writer.write("The difference between the date you wrote an today is $days days")
        }
    }
}

fun main() {
    App(Reader(), Writer()).execute()
}

private fun supportNullableString(line: String?) = line
