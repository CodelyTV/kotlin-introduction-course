package com.codely.demo.cat

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.json.JSONArray
import org.json.JSONObject

class HttpBreedClient(val client: HttpHandler) : BreedRepository {
    override fun findAll(): List<String> {
        val request = Request(Method.GET, "https://api.thecatapi.com/v1/breeds")
            .header("x-api-key", "d3206728-abc1-4295-9b33-ba343b92ec84")
        val response = client(request)
        if (response.status != Status.OK) {
            throw BreedClientException()
        }

        return with(JSONArray(response.bodyString())) {
            (0 until length()).toList().map {
                (this.get(it) as JSONObject).getString("name").lowercase()
            }
        }
    }
}
