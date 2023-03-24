package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.menu.SimpleAppBar
import com.example.movieapp.widgets.MovieList
import com.example.movieapp.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController){
    val movies = getMovies()
    val Favorites = listOf( movies[1], movies[4], movies[6] )
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column {
            SimpleAppBar(menuText = "Favorites", navController = navController)
            MovieList(movies = Favorites, navController = navController)
        }
    }
}