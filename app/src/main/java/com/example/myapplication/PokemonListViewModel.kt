package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor() : ViewModel() {

    private var pokemonList_ : MutableStateFlow<List<PokemonModel>> = MutableStateFlow(emptyList())
    val pokemonList = pokemonList_.asStateFlow()

    private val repository = Repository()

    //Called when the viewmodel is created
    init{
        pokemonList_.value = getPokemonList()
    }

    private fun getPokemonList(): MutableList<PokemonModel> {
        return repository.getPokemonList()
    }

     fun addNewPokemon(){
         viewModelScope.launch {
             val pokemon = repository.getNewPokemon()
             pokemonList_.value = pokemonList_.value + pokemon
         }
    }

}