package com.codely.demo.cat

import java.time.LocalDate
import java.util.UUID

data class Cat(
    val id: UUID,
    val name: String,
    val origin: String,
    val vaccinated: Boolean,
    val dewormed: Boolean,
    val birthDate: LocalDate,
    val createdAt: LocalDate
)
