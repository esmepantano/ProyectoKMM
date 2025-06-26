package com.app.proyectokmm.android.ui.characters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.proyectokmm.android.databinding.ListItemCharacterBinding
import com.app.proyectokmm.domain.model.Character

class CharactersAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private val charactersList = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        Log.d("CharactersAdapter", "Creando ViewHolder")
        return CharacterViewHolder(ListItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItemAt(position)
        Log.d("CharactersAdapter", "Vinculando personaje en posición $position: ${character.name}")
        holder.bind(character)
    }

    override fun getItemCount() = charactersList.size

    fun submitList(characters: List<Character>) {
        Log.d("CharactersAdapter", "submitList llamado con ${characters.size} personajes")
        with(charactersList) {
            clear()
            addAll(characters)
        }
        Log.d("CharactersAdapter", "Lista actualizada, notificando cambios")
        notifyDataSetChanged()
    }

    private fun getItemAt(position: Int) = charactersList[position]
}