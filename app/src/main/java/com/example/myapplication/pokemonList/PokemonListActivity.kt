package com.example.myapplication.pokemonList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.PokemonDataViewModel
import com.example.myapplication.R
import com.example.myapplication.network.PokemonItemDto
import com.example.myapplication.ui.theme.MyApplicationTheme


private val logTag = "PokemonListActivity"

@Composable
fun PokemonListScreen(navController: NavHostController, pokemonDataViewModel: PokemonDataViewModel) {
    val pokemonList = pokemonDataViewModel.pokemonList.collectAsState()
    Log.d(logTag, "PokemonListScreen()")
    LazyColumn {
        items(pokemonList.value.sortedBy { it.id }) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}

@Composable
fun PokemonCard(pokemon: PokemonItemDao){
    Log.d(logTag, "PokemonCard(${pokemon.name})")
        Row(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(2.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))) {//Card
            Image(
                painter = rememberAsyncImagePainter(pokemon.mainImageUrl),
                contentDescription = "Image",
                modifier = Modifier.size(128.dp))

            //name & buttons | types
            Column(
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth(),
            ){
                Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                    //Name and Num
                    PokemonData(pokemon, modifier = Modifier.align(Alignment.TopStart))
                    AuxButtons(modifier = Modifier.align(Alignment.TopEnd))

                }
                Spacer(modifier = Modifier.weight(1f))
                //Types
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    PokemonTypes(modifier = Modifier.align(Alignment.BottomStart))
                }
            }
        }
}

@Composable
fun PokemonData(pokemon: PokemonItemDao, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier){
        Text(text = pokemon.name, modifier = Modifier.padding(8.dp))
        Text(text = "N. ",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(8.dp))
    }
}

@Composable
fun AuxButtons(modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(8.dp)){
        Image(
            painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Image",
            modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.size(4.dp))
        Image(
            painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Image",
            modifier = Modifier.size(24.dp))
    }
}

@Composable
fun PokemonTypes(modifier : Modifier = Modifier){
    Row (modifier = modifier.padding(8.dp)){
        Text(text = "Types")
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Fire")
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Venom")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        PokemonCard(pokemon = PokemonItemDao(1, "Rayquaza", ""))
    }
}