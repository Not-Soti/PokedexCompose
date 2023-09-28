package com.example.myapplication

import com.example.myapplication.network.PokemonModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import javax.inject.Inject

class Repository @Inject constructor(val ktorClient : HttpClient) {

    private val baseUrl = "https://pokeapi.co/api/v2/"

    fun getPokemonList(): MutableList<PokemonModel> {
        return mutableListOf(

        )
    }

    suspend fun getNewPokemon(pokemonName : String): PokemonModel {
        println("Repository - getNewPokemon - Enter")
        kotlin.runCatching { ktorClient.get("${baseUrl}/pokemon/$pokemonName/") }
            .onSuccess {
                println("Repository - getNewPokemon - onSuccess")
                val pokemon : PokemonModel = it.body()
                return pokemon
            }
            .onFailure {
                println("Repository - getNewPokemon - onFailure")
                println(it.message)
            }
        return PokemonModel("","")
    }
}