package com.app.proyectokmm.data.repository

import com.app.proyectokmm.data.cache.CharacterCache
import com.app.proyectokmm.data.remote.MarvelClient
import com.app.proyectokmm.domain.model.Character
import com.app.proyectokmm.domain.repository.CharactersRepository

class KtorCharactersRepository(
    private val api: MarvelClient,
    private val cache: CharacterCache
) : CharactersRepository {

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return try {
            val characters = api.getAllCharacters(timestamp, md5)
            cache.clear()
            cache.insertCharacters(characters)
            characters
        } catch (e: Exception) {
            println("Error fetching from API, loading from cache: ${e.message}")
            cache.getAllCharacters()
        }
    }
}