package com.codely.demo.cat

import com.codely.demo.shared.AgeCalculator
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.format.FormatStyle
import java.util.UUID

data class Cat(
    val id: Id,
    val name: Name,
    val origin: Origin,
    val vaccinated: Vaccinated,
    val birthDate: BirthDate,
    val color: Color,
    val age: Int,
    val createdAt: LocalDate
) {
    enum class Color {
        BLACK, RED, CINNAMON, BLUE, CREAM, LILAC, FAWN, WHITE;

        companion object {
            fun from(value: String?) = if (value.isNullOrBlank() || value.isNullOrEmpty() || !isValid(value)) {
                throw InvalidColor(value)
            } else valueOf(value.uppercase())

            private fun isValid(value: String): Boolean = values().map { it.name }.contains(value.uppercase())
        }
    }

    companion object {
        fun from(
            id: Id,
            name: Name,
            origin: Origin,
            birthDate: BirthDate,
            color: Color,
            vaccinated: Vaccinated,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = vaccinated,
            birthDate = birthDate,
            color = color,
            age = AgeCalculator.calculate(birthDate.value, createdAt).years,
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
                throw InvalidId(value)
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

    data class Vaccinated(val value: Boolean) {
        companion object {
            fun from(value: String?) = if (value.isNullOrBlank() || value.isNullOrEmpty() || !isValid(value)) {
                throw InvalidVaccinated(value)
            } else Vaccinated(value.toBoolean())

            private fun isValid(value: String) = listOf("true", "false").contains(value.lowercase())
        }
    }

    data class BirthDate(val value: LocalDate) {
        companion object {
            fun from(value: String?) = if (value.isNullOrBlank() || value.isNullOrEmpty()) {
                throw InvalidBirthDate(value)
            } else try {
                BirthDate(LocalDate.parse(value))
            } catch (e: DateTimeParseException) {
                throw InvalidBirthDate(value)
            }

        }
    }
}
