package com.codely.demo.cat

class ListCatRepository() : CatRepository {
    private var persistence: List<Cat> = listOf()

    override fun save(cat: Cat) {
        persistence.toMutableList().apply {
            add(cat)
            persistence = toList()
        }
    }
}
