package com.codely.demo

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import java.time.LocalDate
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AppTest {

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDate::class)
        every { LocalDate.now() } returns LocalDate.of(2021, 8, 31)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should calculate the difference and return 31 years`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val app = App(reader, writer)
        every { reader.read() } returns "1990-08-31"

        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 31 years") }
    }

    @Test
    fun `should calculate the difference and return 11 months`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val app = App(reader, writer)
        every { reader.read() } returns "2020-09-01"

        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 11 months") }
    }

    @Test
    fun `should calculate the difference and return 2 days`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val app = App(reader, writer)
        every { reader.read() } returns "2021-08-29"

        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 2 days") }
    }

    @Test
    fun `fail when the introduced date is empty`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val app = App(reader, writer)
        every { reader.read() } returns ""

        app.execute()

        verify { writer.write("The introduced date <> is not valid") }
    }

    @Test
    fun `fail when the introduced date is blank`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val app = App(reader, writer)
        every { reader.read() } returns " "

        app.execute()

        verify { writer.write("The introduced date < > is not valid") }
    }
}
