package com.codely.demo.cat

import com.codely.demo.shared.AgeCalculator
import java.time.LocalDate
import java.util.UUID

data class Cat(
    val id: Id,
    val name: Name,
    val origin: Origin,
    val vaccinated: Boolean,
    val birthDate: LocalDate,
    val color: Color,
    val age: Int,
    val createdAt: LocalDate
) {
    enum class Color {
        BLACK, RED, CINNAMON, BLUE, CREAM, LILAC, FAWN, WHITE;

        companion object {
            fun from(value: String?) = if (value.isNullOrBlank() || value.isNullOrEmpty()) {
                throw InvalidColor(value)
            } else valueOf(value.uppercase())
        }
    }

    companion object {
        fun vaccinatedWith(
            id: Id,
            name: Name,
            origin: Origin,
            birthDate: LocalDate,
            color: Color,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = true,
            birthDate = birthDate,
            color = color,
            age = AgeCalculator.calculate(birthDate, createdAt).years,
            createdAt = createdAt
        )

        fun notVaccinatedWith(
            id: Id,
            name: Name,
            origin: Origin,
            birthDate: LocalDate,
            color: Color,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = false,
            birthDate = birthDate,
            color = color,
            age = AgeCalculator.calculate(birthDate, createdAt).years,
            createdAt = createdAt
        )
    }
    data class Name(val value: String) {
        companion object {
            fun from(value: String?) = if (value.isNullOrBlank() || value.isNullOrEmpty()) {
                throw InvalidName(value)
            } else {
                Name(value)
            }
        }
    }
    data class Id(val value: UUID) {
        companion object {
            fun from(value: String?) = try {
                Id(UUID.fromString(value))
            } catch (exception: Throwable) {
                throw InvalidName(value)
            }
        }
    }

    data class Origin(val value: String) {
        companion object {
            fun from(value: String?) = if (value.isNullOrEmpty() || value.isNullOrBlank()) {
                throw InvalidOrigin(value)
            } else Origin(value)
        }
    }
}
