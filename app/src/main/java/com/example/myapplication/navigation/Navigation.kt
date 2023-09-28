package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.PokemonDataViewModel
import com.example.myapplication.pokemonList.PokemonListScreen
import com.example.myapplication.splashScreen.SplashScreen

@Composable
fun Navigation(pokemonDataViewModel: PokemonDataViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route){
        composable(route = AppScreens.SplashScreen.route){
            SplashScreen(pokemonDataViewModel = pokemonDataViewModel)
        }
        composable(route = AppScreens.PokemonList.route){
            PokemonListScreen(pokemonDataViewModel = pokemonDataViewModel)
        }
    }
}