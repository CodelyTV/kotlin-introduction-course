package com.codely.demo.cat

import com.codely.demo.app.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import java.time.LocalDate
import java.util.UUID

class CatCreator(private val reader: Reader, private val writer: Writer, private val clock: Clock, private val repository: CatRepository) {
    fun create(): Cat {
        writer.write("Please enter an id for your cat")
        val id = reader.read()
        writer.write("Please enter the name of your cat")
        val name = reader.read()
        writer.write("Please enter where your cat came from")
        val origin = reader.read()
        writer.write("Is your cat vaccinated?")
        val vaccinated = reader.read()
        writer.write("What is the color of your cat?")
        val color = reader.read()
        writer.write("When did your cat birth?")
        val birthDate = reader.read()

        if (name.isNullOrBlank() || name.isNullOrEmpty() || origin.isNullOrEmpty() || origin.isNullOrBlank() || color.isNullOrBlank() || color.isNullOrEmpty()) {
            throw IllegalArgumentException()
        } else {
            val cat = if (vaccinated.toBoolean()) {
                Cat.vaccinatedWith(
                    id = UUID.fromString(id),
                    name = name,
                    origin = origin,
                    birthDate = LocalDate.parse(birthDate),
                    color = color,
                    createdAt = clock.now()
                )
            } else {
                Cat.notVaccinatedWith(
                    id = UUID.fromString(id),
                    name = name,
                    origin = origin,
                    color = color,
                    birthDate = LocalDate.parse(birthDate),
                    createdAt = clock.now()
                )
            }
            repository.save(cat)

            return cat
        }
    }
}
