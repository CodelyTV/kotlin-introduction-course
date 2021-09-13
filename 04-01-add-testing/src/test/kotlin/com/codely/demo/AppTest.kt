package com.codely.demo

import java.time.LocalDate

class AppTest(reader: Reader, writer: Writer) : App(reader, writer) {
    override fun currentDate(): LocalDate = LocalDate.parse("2021-08-31")
}
