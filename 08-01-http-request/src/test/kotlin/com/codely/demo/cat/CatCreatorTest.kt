package com.codely.demo.cat

import com.codely.demo.shared.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.http4k.core.HttpHandler
import org.http4k.core.MemoryBody
import org.http4k.core.MemoryResponse
import org.http4k.core.Status
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import kotlin.test.assertEquals

internal class CatCreatorTest {
    private val id = "92efe4c8-fab9-4cb0-82d9-5c75eeca2dc1"
    private val name = "Mandarina"
    private val origin = "Shelter"
    private val vaccinated = "yes"
    private val notVaccinated = "no"
    private val birthDate = "2019-01-01"
    private val fixedDate = LocalDate.of(2021, 8, 31)
    private val color = "red"
    private val breed = "Abyssinian"

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create a vaccinated cat`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen color andThen birthDate andThen breed
        every { client.invoke(any()) } returns MemoryResponse(
            status = Status.OK,
            body = MemoryBody(
                """
            [{
                "alt_names": "",
                "experimental": 0,
                "hairless": 0,
                "hypoallergenic": 0,
                "id": "abys",
                "life_span": "14 - 15",
                "name": "Abyssinian",
                "natural": 1,
                "origin": "Egypt",
                "rare": 0,
                "reference_image_id": null,
                "rex": 0,
                "short_legs": 0,
                "suppressed_tail": 0,
                "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
                "weight_imperial": "7  -  10",
                "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
              }]
                """.trimIndent()
            )
        )

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        creator.create()

        val expectedCat = Cat.from(
            id = Cat.Id.from(id),
            name = Cat.Name.from(name),
            origin = Cat.Origin.from(origin),
            color = Cat.Color.from(color),
            birthDate = Cat.BirthDate.from(birthDate),
            vaccinated = Cat.Vaccinated(true),
            breed = Cat.Breed(breed),
            createdAt = fixedDate
        )

        assertEquals(
            mapOf(
                expectedCat.id to expectedCat
            ),
            repository.findAll()
        )
    }

    @Test
    fun `should create a not vaccinated cat`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen notVaccinated andThen color andThen birthDate andThen breed
        every { client.invoke(any()) } returns MemoryResponse(
            status = Status.OK,
            body = MemoryBody(
                """
            [{
                "alt_names": "",
                "experimental": 0,
                "hairless": 0,
                "hypoallergenic": 0,
                "id": "abys",
                "life_span": "14 - 15",
                "name": "Abyssinian",
                "natural": 1,
                "origin": "Egypt",
                "rare": 0,
                "reference_image_id": null,
                "rex": 0,
                "short_legs": 0,
                "suppressed_tail": 0,
                "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
                "weight_imperial": "7  -  10",
                "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
              }]
                """.trimIndent()
            )
        )

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        creator.create()

        val expectedCat = Cat.from(
            id = Cat.Id.from(id),
            name = Cat.Name.from(name),
            origin = Cat.Origin.from(origin),
            birthDate = Cat.BirthDate.from(birthDate),
            color = Cat.Color.from(color),
            vaccinated = Cat.Vaccinated(false),
            breed = Cat.Breed(breed),
            createdAt = fixedDate
        )

        assertEquals(
            mapOf(
                expectedCat.id to expectedCat
            ),
            repository.findAll()
        )
    }

    @Test
    fun `should fail creating a cat without name`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen "" andThen origin andThen vaccinated andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidName> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with empty name`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen "  " andThen origin andThen vaccinated andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidName> { creator.create() }
    }

    @Test
    fun `should fail creating a cat without shelter`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen "" andThen vaccinated andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidOrigin> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with empty shelter`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen "  " andThen vaccinated andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidOrigin> { creator.create() }
    }

    @Test
    fun `should fail creating a cat without color`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen "" andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidColor> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with empty color`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen " " andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidColor> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with invalid color`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen "orange" andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidColor> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with a non uuid`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns "1" andThen name andThen origin andThen vaccinated andThen color andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidId> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with invalid vaccinated value`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen "maybe" andThen color andThen birthDate
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidVaccinated> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with invalid birth date value`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen color andThen "today"
        every { client.invoke(any()) } returns MemoryResponse(status = Status.OK, body = MemoryBody("[]"))

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidBirthDate> { creator.create() }
    }

    @Test
    fun `should fail creating a cat with invalid breed`() {
        val reader = mockk<Reader>()
        val writer = mockk<Writer>(relaxed = true)
        val clock = mockk<Clock>()
        val repository = InMemoryCatRepository()
        val client = mockk<HttpHandler>()
        val searcher = BreedSearcher(HttpBreedClient(client))
        every { clock.now() } returns fixedDate
        every { client.invoke(any()) } returns MemoryResponse(
            status = Status.OK,
            body = MemoryBody(
                """
            [{
                "alt_names": "",
                "experimental": 0,
                "hairless": 0,
                "hypoallergenic": 0,
                "id": "abys",
                "life_span": "14 - 15",
                "name": "Abyssinian",
                "natural": 1,
                "origin": "Egypt",
                "rare": 0,
                "reference_image_id": null,
                "rex": 0,
                "short_legs": 0,
                "suppressed_tail": 0,
                "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
                "weight_imperial": "7  -  10",
                "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
              }]
                """.trimIndent()
            )
        )
        every { reader.read() } returns id andThen name andThen origin andThen vaccinated andThen color andThen birthDate andThen "Invalid Breed"

        val creator = CatCreator(reader, writer, clock, repository, searcher)
        assertThrows<InvalidBreed> { creator.create() }
    }
}
