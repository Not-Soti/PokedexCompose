package com.example.myapplication.ui.theme

import com.example.myapplication.PokemonModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

class Repository @Inject constructor() {

    fun getPokemonList(): MutableList<PokemonModel> {
        return mutableListOf(
            PokemonModel("Bulbasaur", ""),
            PokemonModel("Squirtle", ""),
            PokemonModel("Charmander", ""),
        )
    }

    suspend fun getNewPokemon(): PokemonModel {
        val ktorClient = HttpClient(CIO){
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        val pokemon : PokemonModel = ktorClient.get("https://pokeapi.co/api/v2/pokemon/ditto").body()

        return pokemon
    }
}