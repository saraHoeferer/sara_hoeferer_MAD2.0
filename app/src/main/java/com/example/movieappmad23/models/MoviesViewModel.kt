package com.example.movieappmad23.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    val favoriteList: List<Movie>
        get() = _movieList.filter { it.isFavorite }

    fun isLiked (movieId: String) {
        _movieList.find{it.id == movieId}?.let { movie ->
            movie.isFavorite = !movie.isFavorite
        }
    }
    var cnt = 5000000000

    fun isNotEmpty(input: String): Boolean{
        return input.isEmpty()
    }

    fun floatIsNotEmpty(input: String): Boolean{
        val regex = "-?\\d+(\\.\\d+)?".toRegex()
        return !input.matches(regex)
    }

    fun genreNotEmpty(input: List<ListItemSelectable>): Boolean{
        for (genre in input){
            if (genre.isSelected){
                return false
            }
        }
        return true
    }

    fun addMovie(title: String, year:String, genres: List<ListItemSelectable>, director: String, actors: String, plot:String, rating: String){
        val fullId = "tt$cnt"
        cnt--

        val selectedGenres = mutableListOf<Genre>()
        for (genre in genres){
            if (genre.isSelected){
                selectedGenres.add(enumValueOf(genre.title))
            }
        }
        _movieList.add(Movie(id=fullId, title=title, year=year, genre=selectedGenres, director = director, actors=actors, plot=plot, rating=rating.toFloat(), images = listOf("https://www.e-ucebnici.mon.gov.mk/naslovni/no-image.png")))
    }

}