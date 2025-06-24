package com.app.proyectokmm.data.repository

import com.app.proyectokmm.data.remote.CharactersResponse
import com.app.proyectokmm.data.remote.MarvelCharactersClient
import com.app.proyectokmm.domain.model.Character
import com.app.proyectokmm.domain.repository.CharactersRepository

class RetrofitCharactersRepository(private val apiClient: MarvelCharactersClient) :
    CharactersRepository {

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return apiClient.getAllCharacters(timestamp, md5).toModel()
    }

    private fun CharactersResponse.toModel(): List<Character> {
        return this.characters.list.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnailUrl = it.thumbnail.toUrl()
            )
        }
    }
}