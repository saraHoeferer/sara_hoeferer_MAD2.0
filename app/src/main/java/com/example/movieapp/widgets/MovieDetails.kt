package com.example.movieapp.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie

@Composable
fun MovieDetails(movie: Movie) {
    // Add text to show the director, release year, genre, actors and rating
    Text(text = "Director: ${movie.director}")
    Text(text = "Released: ${movie.year}")
    Text(text = "Genre: ${movie.genre}")
    Text(text = "Actors: ${movie.actors}")
    Text(text = "Rating: ${movie.rating}")
    // Add a Divider (straight line) before the plot
    Divider(
        color = Color.LightGray,
        thickness = 2.dp,
        modifier = Modifier.padding(vertical = 10.dp)
    )
    // Add text for the plot of the movie
    Text(
        text = "Plot: ${movie.plot}",
        modifier = Modifier
            .padding(horizontal = 20.dp)
    )
}