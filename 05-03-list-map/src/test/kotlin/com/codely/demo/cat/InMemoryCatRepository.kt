package com.codely.demo.cat

import java.util.UUID

class InMemoryCatRepository() : CatRepository {
    private val persistence: MutableMap<UUID, Cat> = mutableMapOf()

    override fun save(cat: Cat) {
        persistence[cat.id] = cat
    }

    fun findAll() = persistence.toMap()
}
