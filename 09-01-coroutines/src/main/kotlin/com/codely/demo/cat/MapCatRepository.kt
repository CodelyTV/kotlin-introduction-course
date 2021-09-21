package com.codely.demo.cat

class MapCatRepository() : CatRepository {
    private var persistence: Map<Cat.Id, Cat> = mapOf()

    override fun save(cat: Cat) {
        persistence.toMutableMap().apply {
            this[cat.id] = cat
            persistence = toMap()
        }
    }
}
