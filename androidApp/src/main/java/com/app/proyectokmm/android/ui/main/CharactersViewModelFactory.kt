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

/*
class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PublicKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiClient = retrofit.create(MarvelCharactersClient::class.java)

        val charactersApi = RetrofitCharactersRepository(apiClient)
        val charactersService = CharactersService(charactersApi)
        return CharactersViewModel(charactersService) as T
    }
}

 */