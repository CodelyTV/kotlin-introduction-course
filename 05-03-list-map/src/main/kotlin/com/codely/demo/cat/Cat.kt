package com.codely.demo.cat

import java.time.LocalDate
import java.util.UUID

data class Cat(
    val id: UUID,
    val name: String,
    val origin: String,
    val vaccinated: Boolean,
    val birthDate: LocalDate,
    val createdAt: LocalDate
) {
    companion object {
        fun vaccinatedWith(
            id: UUID,
            name: String,
            origin: String,
            birthDate: LocalDate,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = true,
            birthDate = birthDate,
            createdAt = createdAt
        )

        fun notVaccinatedWith(
            id: UUID,
            name: String,
            origin: String,
            birthDate: LocalDate,
            createdAt: LocalDate
        ) = Cat(
            id = id,
            name = name,
            origin = origin,
            vaccinated = false,
            birthDate = birthDate,
            createdAt = createdAt
        )
    }
}
