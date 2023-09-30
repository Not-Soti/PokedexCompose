package com.example.myapplication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.PokemonDataViewModel
import com.example.myapplication.pokemonList.PokemonListScreen
import com.example.myapplication.splashScreen.SplashScreen


private val logTag = "Navigation"

@Composable
fun Navigation(/*pokemonDataViewModel: PokemonDataViewModel*/) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home"){
        navigation(startDestination = AppScreens.SplashScreen.route, route = "Home"){
            composable(route = AppScreens.SplashScreen.route){
                Log.d(logTag, "SplashScreen")
                val splashScreenEntry = remember(it){
                    navController.getBackStackEntry(AppScreens.SplashScreen.route)
                }
                val pokemonDataViewModel = hiltViewModel<PokemonDataViewModel>(splashScreenEntry)
                SplashScreen(navController, pokemonDataViewModel = pokemonDataViewModel)
            }
            composable(route = AppScreens.PokemonList.route){
                Log.d(logTag, "PokemonList")
                val pokemonListEntry = remember(it){
                    navController.getBackStackEntry(AppScreens.SplashScreen.route)
                }
                val pokemonDataViewModel = hiltViewModel<PokemonDataViewModel>(pokemonListEntry)
                PokemonListScreen(navController, pokemonDataViewModel = pokemonDataViewModel)
            }
        }

    }
}