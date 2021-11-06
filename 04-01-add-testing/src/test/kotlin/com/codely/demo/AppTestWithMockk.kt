package com.codely.demo

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AppWithMockk {

    @Test
    fun `should calculate the difference and return 31 years`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val app = App(reader, writer, clock)
        every { reader.read() } returns "1990-08-31"
        every { clock.now() } returns LocalDate.parse("2021-08-31")

        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 31 years") }
    }

    @Test
    fun `should calculate the difference and return 11 months`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val app = App(reader, writer, clock)
        every { reader.read() } returns "2020-09-01"
        every { clock.now() } returns LocalDate.parse("2021-08-31")


        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 11 months") }
    }

    @Test
    fun `should calculate the difference and return 2 days`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val app = App(reader, writer, clock)
        every { reader.read() } returns "2021-08-29"
        every { clock.now() } returns LocalDate.parse("2021-08-31")


        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 2 days") }
    }

    @Test
    fun `fail when the introduced date is empty`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val app = App(reader, writer, clock)
        every { reader.read() } returns ""

        app.execute()

        verify { writer.write("The introduced date <> is not valid") }
    }

    @Test
    fun `fail when the introduced date is blank`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val app = App(reader, writer, clock)
        every { reader.read() } returns " "

        app.execute()

        verify { writer.write("The introduced date < > is not valid") }
    }
}
