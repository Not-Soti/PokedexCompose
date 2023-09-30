package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDataViewModel @Inject constructor(val repository : Repository) : ViewModel() {

    private val logTag = "PokemonDataViewModel"

//    private var pokemonList_ : MutableStateFlow<List<PokemonModel>> = MutableStateFlow(emptyList())
//    val pokemonList = pokemonList_.asStateFlow()
    val pokemonList = mutableListOf<PokemonModel>()
    private var pokemonListSize_ : MutableStateFlow<Int> = MutableStateFlow(0)
    val pokemonListSize = pokemonListSize_.asStateFlow()

    private var state_ : MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state = state_.asStateFlow()
    enum class State{
        Loading,
        Loaded
    }

    //Called when the viewmodel is created
    init{
        Log.d(logTag, "init")
        loadPokemon()
    }

     fun loadPokemon(){
         Log.d(logTag, "loadPokemon - Enter")
         viewModelScope.launch {
             Log.d(logTag, "loadPokemon - launch")
             for (pokeId in 1..30){
                 val pokemon = withContext(Dispatchers.IO) { repository.getNewPokemon(pokeId) }
                 pokemonList.add(pokemon)
                 pokemonListSize_.value++
                 Log.d(logTag, "loadPokemon pokemon added")
             }

             if(pokemonList.size == 30){
                 Log.d(logTag, "loadPokemon state loaded")
                 state_.value = State.Loaded
             }
         }
    }

}