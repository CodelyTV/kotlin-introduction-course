package com.codely.demo.cat

class InMemoryCatRepository() : CatRepository {
    private val persistence: MutableMap<Cat.Id, Cat> = mutableMapOf()

    override fun save(cat: Cat) {
        persistence[cat.id] = cat
    }

    fun findAll() = persistence.toMap()
}
