package com.app.proyectokmm.android.ui.characters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.app.proyectokmm.android.databinding.ListItemCharacterBinding
import com.squareup.picasso.Picasso
import com.app.proyectokmm.domain.model.Character

class CharacterViewHolder(private val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.name.text = character.name
        binding.description.text = character.description

        if (character.thumbnailUrl.isNotEmpty()) {
            Picasso.get()
                .load(character.thumbnailUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(binding.image, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                    }
                    override fun onError(e: Exception?) {
                    }
                })
        } else {
            binding.image.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

}