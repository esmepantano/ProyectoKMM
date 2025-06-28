package com.app.proyectokmm.data.remote

import com.app.proyectokmm.core.PRIVATE_KEY
import com.app.proyectokmm.core.PUBLIC_KEY
import com.app.proyectokmm.domain.model.Character
import com.app.proyectokmm.util.currentTimeMillis
import com.app.proyectokmm.util.md5
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MarvelClient(private val client: HttpClient) {

    suspend fun getAllCharacters(timestamp: Long, md5: String): List<Character> {
        val timestamp = currentTimeMillis()
        val hash = md5("$timestamp$PRIVATE_KEY$PUBLIC_KEY")

        val response: CharactersResponse = client.get("https://gateway.marvel.com/v1/public/characters") {
            url {
                parameters.append("ts", timestamp.toString())
                parameters.append("hash", hash)
                parameters.append("apikey", PUBLIC_KEY)
            }
        }.body()

        return response.data.results.map { it.toCharacter() }
    }
}