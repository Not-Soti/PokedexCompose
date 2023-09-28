package com.example.myapplication.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel (var name : String = "", var imageUrl : String = "", var sprites: SpritesModel? = null){

}

@Serializable
data class SpritesModel(var back_default: String? = null,
                        var back_female: String? = null,
                        var back_shiny: String? = null,
                        var back_shiny_female: String? = null,
                        var front_default: String? = null,
                        var front_female: String? = null,
                        var front_shiny: String? = null,
                        var front_shiny_female: String? = null,
                        var other : OtherSprites? = null)

@Serializable
data class OtherSprites(@SerialName("official-artwork") var official_artwork : OfficialArtwork? = null){

}

@Serializable
data class OfficialArtwork(var front_default: String? = null){

}