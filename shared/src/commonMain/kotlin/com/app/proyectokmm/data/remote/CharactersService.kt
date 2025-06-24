package com.app.proyectokmm.data.remote

import com.app.proyectokmm.core.PRIVATE_KEY
import com.app.proyectokmm.core.PUBLIC_KEY
import com.app.proyectokmm.domain.model.Character
import com.app.proyectokmm.domain.repository.CharactersRepository
import com.app.proyectokmm.util.currentTimeMillis
import com.app.proyectokmm.util.md5

class CharactersService(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacters(): List<Character> {
        val timestamp = currentTimeMillis()
        val hash = md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)

        val characters = charactersRepository.getCharacters(
            timestamp,
            hash
        )
        return sort(characters)
    }

    private fun sort(characters: List<Character>): List<Character> {
        return characters.sortedWith(CharacterComparator())
    }

    /**
     * Los personajes se ordenan de la siguiente manera:
     * - Primero los que tienen descripción, y luego los que no.
     * - Los que tienen descripción a su vez se ordenan ascendentemente por su id.
     * - Los que NO tienen descripción se ordenan descendentemente por su id.
     */
    private class CharacterComparator : Comparator<Character> {
        override fun compare(c1: Character, c2: Character): Int {
            if (c1.description.isEmpty() && c2.description.isEmpty()) {
                return c2.id.compareTo(c1.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isNotEmpty()) {
                return c1.id.compareTo(c2.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isEmpty()) {
                return -1
            }
            return 1
        }

    }
}