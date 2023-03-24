package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie

@Composable
fun AnimatedMovieDetails(visible: Boolean, movie: Movie) {
    Row {
        // Add a Animated Visibility method to show/hide the details of a movie
        AnimatedVisibility(visible = visible) {
            // Add a column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 15.dp)
            ) {
                MovieDetails(movie = movie)
            }
        }
    }
}