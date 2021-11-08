package com.codely.demo

import com.codely.demo.app.AppClock
import com.codely.demo.app.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer

fun main() {
    AppClock(Reader(), Writer(), Clock()).execute()
}
