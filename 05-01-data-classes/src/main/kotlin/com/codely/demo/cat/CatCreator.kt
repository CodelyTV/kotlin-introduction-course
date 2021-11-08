package com.codely.demo.cat

import com.codely.demo.shared.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer
import java.time.LocalDate
import java.util.UUID

class CatCreator(val reader: Reader, val writer: Writer, val clock: Clock) {
    fun create(): Cat {
        writer.write("Please enter an id for your cat")
        val id = reader.read()
        writer.write("Please enter the name of your cat")
        val name = reader.read()
        writer.write("Please enter where your cat came from")
        val origin = reader.read()
        writer.write("Is your cat vaccinated?")
        val vaccinated = reader.read()
        writer.write("Is your cat dewormed?")
        val dewormed = reader.read()
        writer.write("When did your cat birth?")
        val birthDate = reader.read()

        if (name.isNullOrBlank() || name.isNullOrEmpty() || origin.isNullOrEmpty() || origin.isNullOrBlank() || vaccinated.isNullOrEmpty() || vaccinated.isNullOrBlank() || dewormed.isNullOrEmpty() || dewormed.isNullOrBlank() || birthDate.isNullOrEmpty() || birthDate.isNullOrBlank()) {
            throw IllegalArgumentException()
        }

        return Cat(
            id = UUID.fromString(id),
            name = name,
            origin = origin,
            vaccinated = vaccinated.toBoolean(),
            dewormed = dewormed.toBoolean(),
            birthDate = LocalDate.parse(birthDate),
            createdAt = clock.now()
        )
    }
}
