package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repository.Repository
import com.example.myapplication.pokemonList.PokemonItemDao
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

    private var pokemonList_ : MutableStateFlow<List<PokemonItemDao>> = MutableStateFlow(emptyList())
    val pokemonList = pokemonList_.asStateFlow()

    //Needed to share the number of items in the list as
    // pokemonList_.asStateFlow() does not have a .size() value
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

         for (pokeId in 1..30){
             viewModelScope.launch {
                 Log.d(logTag, "loadPokemon - getting pokemon $pokeId")
                 val pokemon = withContext(Dispatchers.IO) { repository.getNewPokemon(pokeId) }
                 pokemonList_.value = pokemonList_.value + pokemon
                 pokemonListSize_.value++
                 if(pokemonList_.value.size == 30){
                     state_.value = State.Loaded
                 }
                 Log.d(logTag, "loadPokemon pokemon $pokeId added")
             }
         }

    }

}