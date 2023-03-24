package com.example.movieapp.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.models.Movie
import com.example.movieapp.screens.Screen

// Function used to display all the movies in a column
@Composable
fun MovieList(movies: List<Movie>, navController: NavController) {
    // Use LazyColumn to only load visible movies
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate(route = Screen.DetailScreen.route + "/$movieId")
            }
        }
    }
}