package com.codely.demo.cat

import org.json.JSONObject

class BreedSearcher(private val client: BreedClient) {
    fun search(): List<Cat.Breed> {
        with(client.findAll()) {
            return (0 until length()).toList().map {
                (this.get(it) as JSONObject).getString("name").let {  name ->
                    Cat.Breed(name.lowercase())
                }
            }
        }
    }
}
