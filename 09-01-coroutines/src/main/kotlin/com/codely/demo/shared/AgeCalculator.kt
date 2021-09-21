package com.codely.demo.shared

import java.time.LocalDate
import java.time.Period

object AgeCalculator {
    fun calculate(birthDate: LocalDate, now: LocalDate) = Period.between(birthDate, now)
}
