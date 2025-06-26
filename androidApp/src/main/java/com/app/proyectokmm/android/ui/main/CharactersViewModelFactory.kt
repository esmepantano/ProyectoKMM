package com.app.proyectokmm.android.ui.main

import com.app.proyectokmm.data.remote.MarvelClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.proyectokmm.data.remote.CharactersService
import com.app.proyectokmm.data.remote.createHttpClient
import com.app.proyectokmm.data.repository.KtorCharactersRepository

class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val ktorClient = createHttpClient()

        val marvelClient = MarvelClient(ktorClient)

        val repository = KtorCharactersRepository(marvelClient)

        val charactersService = CharactersService(repository)

        return CharactersViewModel(charactersService) as T
    }
}