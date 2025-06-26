package com.app.proyectokmm.data.repository

import com.app.proyectokmm.data.remote.MarvelClient
import com.app.proyectokmm.domain.model.Character
import com.app.proyectokmm.domain.repository.CharactersRepository

class KtorCharactersRepository(private val api: MarvelClient) : CharactersRepository {
    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return api.getAllCharacters()
    }
}