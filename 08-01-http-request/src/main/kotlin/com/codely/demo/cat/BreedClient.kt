package com.codely.demo.cat

import org.json.JSONArray

interface BreedClient {
    fun findAll(): JSONArray
}

class BreedClientException(override val message: String = "Something went wrong searching the breed list"): RuntimeException()

