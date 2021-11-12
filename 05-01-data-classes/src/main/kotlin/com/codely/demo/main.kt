package com.codely.demo

import com.codely.demo.cat.CatCreator
import com.codely.demo.shared.Clock
import com.codely.demo.shared.Reader
import com.codely.demo.shared.Writer

fun main() {
    CatCreator(Reader(), Writer(), Clock()).create()
}
