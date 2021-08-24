package com.codely.demo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.format.DateTimeParseException
import kotlin.test.assertContains

class AppTest {

    @Test
    fun `should calculate the difference and return 31 years`() {
        val app = AppSewing()
        app.execute("1990-08-31")

        assertContains(app.lastMessage, "The difference between the date you wrote an today is 31 years")
    }

    @Test
    fun `should calculate the difference and return 11 months`() {
        val app = AppSewing()
        app.execute("2020-09-01")

        assertContains(app.lastMessage, "The difference between the date you wrote an today is 11 months")
    }

    @Test
    fun `should calculate the difference and return 2 days`() {
        val app = AppSewing()
        app.execute("2021-08-29")

        assertContains(app.lastMessage, "The difference between the date you wrote an today is 2 days")
    }

    @Test
    fun `fail when the introduced date is malformed`() {
        val app = AppSewing()
        assertThrows<DateTimeParseException> { app.execute("2021-8-31") }
    }

    @Test
    fun `fail when the introduced date is empty`() {
        val app = AppSewing()
        app.execute("")

        assertContains(app.lastMessage, "The introduced date <> is not valid")
    }

    @Test
    fun `fail when the introduced date is blank`() {
        val app = AppSewing()
        app.execute(" ")

        assertContains(app.lastMessage, "The introduced date < > is not valid")
    }

}

class AppSewing: App() {
    var lastMessage: String = ""
    override fun currentDate() = LocalDate.parse("2021-08-31")
    override fun show(message:String) {
        lastMessage = "$lastMessage | $message"
    }
}