package com.codely.demo.cat

import com.codely.demo.cat.Cat.Id
import com.codely.demo.cat.Cat.Name
import com.codely.demo.shared.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import java.time.LocalDate

class CatCreator(
    private val reader: Reader,
    private val writer: Writer,
    private val clock: Clock,
    private val repository: CatRepository
) {
    fun create(): Cat {
        val id = Id.from(obtainInput("Please enter an id for your cat"))
        val name = Name.from(obtainInput("Please enter the name of your cat"))
        val origin = Cat.Origin.from(obtainInput("Please enter where your cat came from"))
        val vaccinated = obtainInput("Is your cat vaccinated?")
        val color = obtainInput("What is the color of your cat?")
        val birthDate = obtainInput("When did your cat birth <yyyy-MM-dd>?")

        if (color.isNullOrBlank() || color.isNullOrEmpty()) {
            throw InvalidColor(color)
        }
        if (vaccinated.isNullOrBlank() || vaccinated.isNullOrEmpty()) {
            throw InvalidVaccinated(vaccinated)
        }

        if (birthDate.isNullOrBlank() || birthDate.isNullOrEmpty()) {
            throw InvalidBirthDate(birthDate)
        }

        return cat(id, name, origin, vaccinated, birthDate, color).apply {
            repository.save(this)
        }.also {
            writer.write("Your cat has been successfully created $it")
        }
    }

    private fun obtainInput(message: String) = writer.write(message).run { reader.read() }

    private fun cat(
        id: Id,
        name: Name,
        origin: Cat.Origin,
        vaccinated: String,
        birthDate: String,
        color: String
    ): Cat {
        return if (vaccinated.toBoolean()) {
            Cat.vaccinatedWith(
                id = id,
                name = name,
                origin = origin,
                birthDate = LocalDate.parse(birthDate),
                color = color,
                createdAt = clock.now()
            )
        } else {
            Cat.notVaccinatedWith(
                id = id,
                name = name,
                origin = origin,
                color = color,
                birthDate = LocalDate.parse(birthDate),
                createdAt = clock.now()
            )
        }
    }
}
