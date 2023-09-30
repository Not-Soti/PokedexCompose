package com.example.myapplication.network

import com.example.myapplication.pokemonList.PokemonItemDao
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItemDto (@SerialName("name")var name : String = "",
                           @SerialName("id")var id : Int,
                           @SerialName("sprites")var sprites: SpritesModel? = null,
){
    fun toPokemonItemDao() : PokemonItemDao{
        return PokemonItemDao(id, name, sprites?.other?.official_artwork?.front_default.toString())
    }
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