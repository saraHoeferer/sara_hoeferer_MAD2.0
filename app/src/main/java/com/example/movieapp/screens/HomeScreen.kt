package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.menu.MainAppBar
import com.example.movieapp.widgets.MovieList
import com.example.movieapp.models.getMovies

@Composable
fun HomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column() {
            MainAppBar(navController = navController)
            MovieList(movies = getMovies(), navController)
        }

    }
}
