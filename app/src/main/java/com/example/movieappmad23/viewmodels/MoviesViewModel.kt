package com.example.movieappmad23.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies
import java.util.*

// inherit from ViewModel class
class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()   //get all movies and create an observable state list
    val movieList: List<Movie>  // expose previously created list but immutable
        get() = _movieList

    // validation fields
    var isEnabledSaveButton: MutableState<Boolean> = mutableStateOf(false)

    var title = mutableStateOf("")
    var isTitleValid: MutableState<Boolean> = mutableStateOf(false)
    var titleErrMsg: MutableState<String> = mutableStateOf("")

    var year = mutableStateOf("")
    var isYearValid: MutableState<Boolean> = mutableStateOf(false)
    var yearErrMsg: MutableState<String> = mutableStateOf("")

    var director = mutableStateOf("")
    var isDirectorValid: MutableState<Boolean> = mutableStateOf(false)
    var directorErrMsg: MutableState<String> = mutableStateOf("")

    var actors = mutableStateOf("")
    var isActorsValid: MutableState<Boolean> = mutableStateOf(false)
    var actorsErrMsg: MutableState<String> = mutableStateOf("")

    var plot = mutableStateOf("")

    private val _allGenres : List<Genre> = Genre.values().toList()

    var selectableGenreItems = _allGenres.map {genre ->
        ListItemSelectable(
            title = genre.toString(),
            isSelected = false
        )
    }.toMutableStateList()
    /*
    var selectableGenreItems: List<ListItemSelectable> = listOf()
        get() = _allGenres.value.map { genre ->
            ListItemSelectable(
                title = genre.toString(),
                isSelected = false
            )
        }

     */
    private val selectedGenres: List<Genre> = selectableGenreItems.filter { item -> item.isSelected }
        .map { listItemSelectable ->
            Genre.valueOf(listItemSelectable.title)
        }
    /*
    var selectableGenreItems = mutableStateListOf(
            _allGenres.map { genre ->
                ListItemSelectable(
                    title = genre.toString(),
                    isSelected = false
                )
            }
        )


     */


    var rating = mutableStateOf("")
    var isRatingValid: MutableState<Boolean> = mutableStateOf(false)
    var ratingErrMsg: MutableState<String> = mutableStateOf("")

    val favoriteMovies: List<Movie>
        get() = _movieList.filter { it.isFavorite == true }

    // find provided movie by id and toggle its isFavorite attribute
    fun toggleFavorite(movie: Movie) = movieList.find { it.id == movie.id }?.let { movie ->
        movie.isFavorite = !movie.isFavorite
    }

    fun selectGenre(selectedItem: ListItemSelectable) {
        selectableGenreItems = selectableGenreItems.map {
            if (it.title == selectedItem.title) {
                selectedItem.copy(isSelected = !selectedItem.isSelected)
            } else {
                it
            }
        }.toMutableStateList()
    }
    private fun shouldEnableAddButton() {
        isEnabledSaveButton.value = titleErrMsg.value.isEmpty()
                && yearErrMsg.value.isEmpty()
                && directorErrMsg.value.isEmpty()
                && actorsErrMsg.value.isEmpty()
                && ratingErrMsg.value.isEmpty()
    }

    fun validateTitle() {
        if (title.value.trim().isNotEmpty()) {
            isTitleValid.value = true
            titleErrMsg.value = ""
        } else {
            isTitleValid.value = false
            titleErrMsg.value = "Title is required"
        }
        shouldEnableAddButton()
    }

    fun validateYear() {
        if (year.value.trim().isNotEmpty()) {
            isYearValid.value = true
            yearErrMsg.value = ""
        } else {
            isYearValid.value = false
            yearErrMsg.value = "Year is required"
        }
        shouldEnableAddButton()
    }

    fun validateDirector() {
        if (director.value.trim().isNotEmpty()) {
            isDirectorValid.value = true
            directorErrMsg.value = ""
        } else {
            isDirectorValid.value = false
            directorErrMsg.value = "Director is required"
        }
        shouldEnableAddButton()
    }

    fun validateActors() {
        if (actors.value.trim().isNotEmpty()) {
            isActorsValid.value = true
            actorsErrMsg.value = ""
        } else {
            isActorsValid.value = false
            actorsErrMsg.value = "Actors is required"
        }
        shouldEnableAddButton()
    }

    fun validateRating(){
        val ratingVal = rating.value.toFloatOrNull()
        if(rating.value.trim().isNotEmpty()
            && !rating.value.startsWith("0")
            && (ratingVal != null)
            && (ratingVal >= 0)
            && (ratingVal <= 10)
        ){
            isRatingValid.value = true
            ratingErrMsg.value = ""
        } else {
            isRatingValid.value = false
            ratingErrMsg.value = "Rating is required and must be valid decimal format."
        }

        shouldEnableAddButton()
    }

    fun validateGenres(){

    }

    fun addMovie() {
        val movie = Movie(
            id = UUID.randomUUID().toString(),
            title = title.value,
            director = director.value,
            actors = actors.value,
            plot = plot.value.trim(),
            genre = selectedGenres,
            year = year.value,
            images = listOf(),
            rating = rating.value.toFloat()
        )

        Log.i("addMovie", movie.genre.toString())
    }


}