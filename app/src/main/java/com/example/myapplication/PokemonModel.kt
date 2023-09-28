package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel (var name : String = "", var imageUrl : String = ""){

}