package com.app.proyectokmm.data.remote

import com.app.proyectokmm.domain.model.Character
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CharactersResponse(
    @SerialName("data") val data: CharacterData
)

@Serializable
data class CharacterData(
    @SerialName("results") val results: List<CharacterResult>
)

@Serializable
data class CharacterResult(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnail") val thumbnail: Thumbnail
) {
    fun toCharacter(): Character {
        return Character(
            id = id,
            name = name,
            description = description,
            thumbnailUrl = thumbnail.toUrl()
        )
    }
}

@Serializable
data class Thumbnail(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String
) {
    fun toUrl(): String {
        return "$path.$extension".replace("http://", "https://")
    }
}