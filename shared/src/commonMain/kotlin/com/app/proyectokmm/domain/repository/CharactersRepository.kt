package com.app.proyectokmm.domain.repository

import com.app.proyectokmm.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>
}