package com.codely.demo.cat

import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.json.JSONArray

class HttpBreedClient : BreedClient {
    override fun findAll(): JSONArray {
        val request = Request(Method.GET, "https://api.thecatapi.com/v1/breeds")
            .header("x-api-key", "d3206728-abc1-4295-9b33-ba343b92ec84")
        val client: HttpHandler = JavaHttpClient()
        val response = client(request)
        if (response.status != Status.OK) {
            throw BreedClientException()
        }

        return JSONArray(response.bodyString())
    }
}
