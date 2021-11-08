package com.codely.demo.cat

interface BreedRepository {
    fun findAll(): List<String>
}

class BreedClientException(override val message: String = "Something went wrong searching the breed list") : RuntimeException()
