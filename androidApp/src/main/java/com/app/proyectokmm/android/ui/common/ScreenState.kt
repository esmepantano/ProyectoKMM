package com.app.proyectokmm.android.ui.common

import com.app.proyectokmm.domain.model.Character

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<Character>) : ScreenState()
}