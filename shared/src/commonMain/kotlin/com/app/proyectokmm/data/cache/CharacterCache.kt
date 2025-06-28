package com.app.proyectokmm.data.cache

import com.app.proyectokmm.cache.AppDatabase
import com.app.proyectokmm.domain.model.Character

class CharacterCache(private val database: AppDatabase) {

    fun getAllCharacters(): List<Character> {
        return database.appDatabaseQueries.selectAll().executeAsList().map {
            Character(it.id, it.name, it.description, it.thumbnailUrl)
        }
    }

    fun insertCharacters(characters: List<Character>) {
        database.appDatabaseQueries.transaction {
            characters.forEach {
                database.appDatabaseQueries.insertCharacter(
                    it.id,
                    it.name,
                    it.description,
                    it.thumbnailUrl
                )
            }
        }
    }

    fun clear() {
        database.appDatabaseQueries.deleteAll()
    }
}