package com.codely.demo.cat

import java.time.LocalDate
import java.util.UUID

data class Cat(
    val id: UUID,
    val name: String,
    val origin: String,
    val vaccinated: Boolean,
    val birthDate: LocalDate,
    val color: Color,
    val createdAt: LocalDate
) {
    enum class Color {
        BLACK, RED, CINNAMON, BLUE, CREAM, LILAC, FAWN, WHITE
    }

    companion object {
        fun vaccinatedWith(
            id: UUID,
            name: String,
            origin: String,
            birthDate: LocalDate,
            color: String,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = true,
            birthDate = birthDate,
            color = Color.valueOf(color.uppercase()),
            createdAt = createdAt
        )

        fun notVaccinatedWith(
            id: UUID,
            name: String,
            origin: String,
            birthDate: LocalDate,
            color: String,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = false,
            birthDate = birthDate,
            color = Color.valueOf(color.uppercase()),
            createdAt = createdAt
        )
    }
}
