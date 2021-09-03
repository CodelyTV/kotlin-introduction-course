package com.codely.demo.cat

class MutableListCatRepository() : CatRepository {
    private val persistence: MutableList<Cat> = mutableListOf()

    override fun save(cat: Cat) {
        persistence.add(cat)
    }
}
