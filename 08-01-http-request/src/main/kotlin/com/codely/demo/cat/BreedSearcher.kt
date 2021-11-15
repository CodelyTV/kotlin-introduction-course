package com.codely.demo.cat

class BreedSearcher(private val client: BreedRepository) {
    fun search(): List<Cat.Breed> {
        return client.findAll().map {
            Cat.Breed(it)
        }
    }
}
