package com.codely.demo.cat

import com.codely.demo.shared.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import java.time.LocalDate
import java.util.UUID

class CatCreator(private val reader: Reader, private val writer: Writer, private val clock: Clock, private val repository: CatRepository) {
    fun create(): Cat {
        val id = obtainInput("Please enter an id for your cat")
        val name = Name.from(obtainInput("Please enter the name of your cat"))
        val origin = obtainInput("Please enter where your cat came from")
        val vaccinated = obtainInput("Is your cat vaccinated?")
        val color = obtainInput("What is the color of your cat?")
        val birthDate = obtainInput("When did your cat birth?")

        if (origin.isNullOrEmpty() || origin.isNullOrBlank()) {
            throw InvalidOrigin(origin)
        }

        val cat = if (vaccinated.toBoolean()) {
            Cat.vaccinatedWith(
                id = UUID.fromString(id),
                name = name.value,
                origin = origin,
                birthDate = LocalDate.parse(birthDate),
                color = Cat.Color.from(color).name,
                createdAt = clock.now()
            )
        } else {
            Cat.notVaccinatedWith(
                id = UUID.fromString(id),
                name = name.value,
                origin = origin,
                color = Cat.Color.from(color),
                birthDate = LocalDate.parse(birthDate),
                createdAt = clock.now()
            )
        }
        repository.save(cat)

        return cat
    }

    private fun obtainInput(message: String) = writer.write(message).run { reader.read() }
}
