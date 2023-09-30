package com.example.myapplication.splashScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myapplication.PokemonDataViewModel
import com.example.myapplication.navigation.AppScreens

private val logTag = "SplashScreen"
@Composable
fun SplashScreen(navController: NavHostController, pokemonDataViewModel: PokemonDataViewModel) {
    Log.d(logTag, "SplashScreen - Enter")

    //pokemonDataViewModel.loadPokemon()

    val viewModelState by pokemonDataViewModel.state.collectAsState()


    if(viewModelState == PokemonDataViewModel.State.Loaded) {
        Log.d(logTag, "SplashScreen - Loaded")
        navController.navigate(AppScreens.PokemonList.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }else{
        Log.d(logTag, "SplashScreen - Loading")
        ShowLoadedPokemon(pokemonDataViewModel)
    }
}

@Composable
fun ShowLoadedPokemon(pokemonDataViewModel: PokemonDataViewModel) {
    Log.d(logTag, "ShowLoadedPokemon - Enter")
    val pokemonListSize by pokemonDataViewModel.pokemonListSize.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator(modifier = Modifier.size(20.dp))
        Text(text = pokemonListSize.toString())
    }
}