package com.example.movieappmad23.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()//MutableStateFlow<List<Movie>>(emptyList())
    val movieList: List<Movie>
        get() = _movieList

    val favoriteMovies: List<Movie>
        get() = _movieList.filter { it.isFavorite == true }

    // find provided movie by id and toggle its isFavorite attribute
    fun toggleFavorite(movie: Movie) = movieList.find { it.id == movie.id }?.let { movie ->
            movie.isFavorite = !movie.isFavorite
        }


}