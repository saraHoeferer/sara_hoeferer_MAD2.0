package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun add(movie: Movie) = movieDao.addMovie(movie)
    suspend fun delete(movie: Movie) = movieDao.deleteMovie(movie)
    suspend fun update(movie: Movie) = movieDao.updateMovie(movie)
    fun readAll() = movieDao.readAllMovies()
    fun readAllFavorites() = movieDao.readAllFavoriteMovies()
    suspend fun getMovieById(movieId: String): Movie = movieDao.getMovieById(movieId)
}