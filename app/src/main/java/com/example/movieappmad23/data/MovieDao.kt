package com.example.movieappmad23.data

import androidx.room.*
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * from movie")
    fun readAllMovies(): Flow<List<Movie>>

    @Query("SELECT * from movie where isFavorite = 1")
    fun readAllFavoriteMovies(): Flow<List<Movie>>

    @Query("SELECT * from movie where id=:movieId")
    suspend fun getMovieById(movieId: String): Movie
}