package com.example.myapplication.navigation

sealed class AppScreens (val route: String){
    object PokemonList : AppScreens("pokemon_list")
    object SplashScreen : AppScreens("splash_screen")
}