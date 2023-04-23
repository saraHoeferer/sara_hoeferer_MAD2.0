package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository

class DetailViewModel (private val repository: MovieRepository): ViewModel() {
    suspend fun getMovieById(movieId: String): Movie {
        return repository.getMovieById(movieId)
    }

    suspend fun updateFavoriteMovies(movie: Movie){
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }
}