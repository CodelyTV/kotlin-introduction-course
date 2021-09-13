package com.codely.demo.cat

import com.codely.demo.app.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDate
import java.util.UUID
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CatCreatorTest {
        private val id = "92efe4c8-fab9-4cb0-82d9-5c75eeca2dc1"
        private val name = "Mandarina"
        private val origin = "Shelter"
        private val vaccinated = "true"
        private val birthDate = "2019-01-01"
        private val fixedDate = LocalDate.of(2021, 8, 31)

    @Test
    fun `should create a cat`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen birthDate

        val creator = CatCreator(reader, writer, clock)
        val actualCat = creator.create()

        val expectedCat = Cat(
            UUID.fromString(id),
            name,
            origin,
            vaccinated.toBoolean(),
            LocalDate.parse(birthDate),
            fixedDate
        )

        assertEquals(expectedCat, actualCat)
    }

    @Test
    fun `should fail creating a cat without name`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen "" andThen origin andThen vaccinated andThen birthDate

        val creator = CatCreator(reader, writer, clock)
        assertThrows<IllegalArgumentException> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with empty name`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen "  " andThen origin andThen vaccinated andThen birthDate

        val creator = CatCreator(reader, writer, clock)
        assertThrows<IllegalArgumentException> { creator.create() }
    }

    @Test
    fun `should fail creating a cat without shelter`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen "" andThen vaccinated andThen birthDate

        val creator = CatCreator(reader, writer, clock)
        assertThrows<IllegalArgumentException> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with empty shelter`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen "  " andThen vaccinated andThen birthDate

        val creator = CatCreator(reader, writer, clock)
        assertThrows<IllegalArgumentException> { creator.create() }
    }
}
