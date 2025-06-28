package com.app.proyectokmm.android.ui.main

import android.content.Context
import com.app.proyectokmm.data.remote.MarvelClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.app.proyectokmm.cache.AppDatabase
import com.app.proyectokmm.data.cache.CharacterCache
import com.app.proyectokmm.data.remote.CharactersService
import com.app.proyectokmm.data.remote.createHttpClient
import com.app.proyectokmm.data.repository.KtorCharactersRepository

class CharactersViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val ktorClient = createHttpClient()

        val marvelClient = MarvelClient(ktorClient)

        val driver = AndroidSqliteDriver(AppDatabase.Schema, context, "characters.db")
        val database = AppDatabase(driver)
        val cache = CharacterCache(database)

        val repository = KtorCharactersRepository(marvelClient, cache)

        val charactersService = CharactersService(repository)

        return CharactersViewModel(charactersService) as T
    }
}