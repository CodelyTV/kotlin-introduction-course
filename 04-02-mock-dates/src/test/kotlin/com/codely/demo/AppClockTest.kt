package com.codely.demo

import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import java.time.LocalDate
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class AppClockTest {

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should calculate the difference and return 31 years`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        every { clock.now() } returns LocalDate.of(2021, 8, 31)
        every { reader.read() } returns "1990-08-31"

        val app = AppClock(reader, writer, clock)

        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 31 years") }
    }

    @Test
    fun `should calculate the difference and return 11 months`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        every { reader.read() } returns "2020-09-01"
        val clock = mockk<Clock>()
        every { clock.now() } returns LocalDate.of(2021, 8, 31)

        val app = AppClock(reader, writer, clock)
        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 11 months") }
    }

    @Test
    fun `should calculate the difference and return 2 days`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        every { reader.read() } returns "2021-08-29"
        val clock = mockk<Clock>()
        every { clock.now() } returns LocalDate.of(2021, 8, 31)

        val app = AppClock(reader, writer, clock)
        app.execute()

        verify { writer.write("The difference between the date you wrote an today is 2 days") }
    }

    @Test
    fun `fail when the introduced date is empty`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        every { reader.read() } returns ""
        val clock = mockk<Clock>()
        every { clock.now() } returns LocalDate.of(2021, 8, 31)

        val app = AppClock(reader, writer, clock)
        app.execute()

        verify { writer.write("The introduced date <> is not valid") }
    }

    @Test
    fun `fail when the introduced date is blank`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        every { reader.read() } returns " "
        val clock = mockk<Clock>()
        every { clock.now() } returns LocalDate.of(2021, 8, 31)

        val app = AppClock(reader, writer, clock)
        app.execute()

        verify { writer.write("The introduced date < > is not valid") }
    }
}
