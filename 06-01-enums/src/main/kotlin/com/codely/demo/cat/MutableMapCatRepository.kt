package com.codely.demo.cat

import java.util.UUID

class MutableMapCatRepository() : CatRepository {
    private val persistence: MutableMap<UUID, Cat> = mutableMapOf()

    override fun save(cat: Cat) {
        persistence[cat.id] = cat
    }
}
