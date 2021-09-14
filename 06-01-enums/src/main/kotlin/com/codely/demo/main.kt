package com.codely.demo

import com.codely.demo.app.App
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer

fun main() {
    AppClock(Reader(), Writer(), Clock()).execute()
}
