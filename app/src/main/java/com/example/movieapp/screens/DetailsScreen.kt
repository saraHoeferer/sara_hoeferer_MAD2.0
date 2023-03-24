package com.example.movieapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.menu.SimpleAppBar
import com.example.movieapp.widgets.ImageRow
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.models.getSpecificMovie

@Composable
fun DetailsScreen(movieId: String?, navController: NavController) {
    val movie = getSpecificMovie(movieId)
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column() {
            SimpleAppBar(menuText = movie.title, navController =navController)
            MovieRow(movie = movie)
            ImageRow(movieImages = movie.images)
        }
    }
}
