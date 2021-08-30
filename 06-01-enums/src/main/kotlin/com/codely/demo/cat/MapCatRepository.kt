package com.codely.demo.cat

import java.util.UUID

class MapCatRepository() : CatRepository {
    private var persistence: Map<UUID, Cat> = mapOf()

    override fun save(cat: Cat) {
        persistence.toMutableMap().apply {
            this[cat.id] = cat
            persistence = toMap()
        }
    }
}
