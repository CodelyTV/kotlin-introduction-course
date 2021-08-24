package com.codely.demo

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

class AppTest {

    @Test
    fun `should calculate the difference and return 31 years`() {
        val reader = mock<Reader>()
        val writer = mock<Writer>()
        val app = AppSewing(reader, writer)
        whenever(reader.read()).thenReturn("1990-08-31")
        doNothing().`when`(writer).write(any())

        app.execute()

        verify(writer).write("The difference between the date you wrote an today is 31 years")
    }

    @Test
    fun `should calculate the difference and return 11 months`() {
        val reader = mock<Reader>()
        val writer = mock<Writer>()
        val app = AppSewing(reader, writer)
        whenever(reader.read()).thenReturn("2020-09-01")
        doNothing().`when`(writer).write(any())

        app.execute()

        verify(writer).write("The difference between the date you wrote an today is 11 months")
    }

    @Test
    fun `should calculate the difference and return 2 days`() {
        val reader = mock<Reader>()
        val writer = mock<Writer>()
        val app = AppSewing(reader, writer)
        doNothing().`when`(writer).write(any())
        whenever(reader.read()).thenReturn("2021-08-29")

        app.execute()

        verify(writer).write("The difference between the date you wrote an today is 2 days")
    }

    @Test
    fun `fail when the introduced date is empty`() {
        val reader = mock<Reader>()
        val writer = mock<Writer>()
        val app = AppSewing(reader, writer)
        doNothing().`when`(writer).write(any())
        whenever(reader.read()).thenReturn("")

        app.execute()

        verify(writer).write("The introduced date <> is not valid")
    }

    @Test
    fun `fail when the introduced date is blank`() {
        val reader = mock<Reader>()
        val writer = mock<Writer>()
        val app = AppSewing(reader, writer)
        doNothing().`when`(writer).write(any())
        whenever(reader.read()).thenReturn(" ")

        app.execute()

        verify(writer).write("The introduced date < > is not valid")
    }

}

class AppSewing(reader: Reader, writer: Writer) : App(reader, writer) {
    override fun currentDate() = LocalDate.parse("2021-08-31")
}