package com.app.proyectokmm.android.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.proyectokmm.android.ui.common.ScreenState
import com.app.proyectokmm.data.remote.CharactersService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val charactersService: CharactersService) : ViewModel() {

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: Flow<ScreenState> = _screenState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val list = charactersService.getCharacters()
                _screenState.value = ScreenState.ShowCharacters(list)
            } catch (e: Exception) {
            }
        }
    }

}