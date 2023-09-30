package com.example.myapplication.Repository

import com.example.myapplication.network.PokemonItemDto
import com.example.myapplication.pokemonList.PokemonItemDao
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import javax.inject.Inject

class Repository @Inject constructor(val ktorClient : HttpClient) {

    private val baseUrl = "https://pokeapi.co/api/v2/"

    suspend fun getNewPokemon(pokemonName: Int): PokemonItemDao {
        println("Repository - getNewPokemon - Enter")
        kotlin.runCatching { ktorClient.get("${baseUrl}/pokemon/$pokemonName/") }
            .onSuccess {
                println("Repository - getNewPokemon - onSuccess")
                val pokemon : PokemonItemDto = it.body()
                return pokemon.toPokemonItemDao()
            }
            .onFailure {
                println("Repository - getNewPokemon - onFailure")
                println(it.message)
            }
        return PokemonItemDao(0, "", "")
    }
}