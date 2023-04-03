package com.example.movieappmad23.screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie

data class AddMovieUiState(
    val title: String = "",
    val year: String = "",
    val genre: List<Genre> = listOf(),
    val director: String = "",
    val actors: String = "",
    val plot: String = "",
    val images: List<String> = listOf(),
    val rating: String = "",
    val actionEnabled: Boolean = false,
    var selectableGenreItems: SnapshotStateList<ListItemSelectable> = Genre.values().toList().map { genre ->
        ListItemSelectable(title = genre.toString())
    }.toMutableStateList(),
    var titleErr: Boolean = false,
    val yearErr: Boolean = false,
    val directorErr: Boolean = false,
    val actorsErr: Boolean = false,
    val ratingErr: Boolean = false,
    val genreErr: Boolean = false,
)

data class InputField(
    val text: String = "",
    val err: String = "",
    val valid: Boolean = false
)

fun AddMovieUiState.selectGenre(item: ListItemSelectable): List<Genre> {
    selectableGenreItems.find { it.title == item.title }?.let { genre ->
        genre.isSelected = !genre.isSelected
    }

    return selectableGenreItems.filter { item -> item.isSelected }.map { listItemSelectable ->
        Genre.valueOf(listItemSelectable.title)
    }
}

fun AddMovieUiState.toMovie(): Movie = Movie(
    title = title,
    year = year,
    genre = genre,
    director = director,
    actors = actors,
    plot = plot,
    images = images,
    rating = rating.toDoubleOrNull() ?: 0.0
)

fun AddMovieUiState.toMovieUiState(actionEnabled: Boolean): AddMovieUiState = AddMovieUiState(
    title = title,
    year = year,
    genre = genre,
    director = director,
    actors = actors,
    plot = plot,
    images = images,
    rating = rating.toString(),
    actionEnabled = actionEnabled
)

fun AddMovieUiState.isValid() : Boolean {
    return title.isNotBlank() && year.isNotBlank() && genre.isNotEmpty() && director.isNotBlank() &&
            actors.isNotBlank() && rating.isNotBlank()
}

/*
fun validateYear() {
    if (year.value.trim().isNotEmpty()) {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isYearValid = true,
                yearErrMsg = ""
            )
        }
    } else {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isYearValid = false,
                yearErrMsg = "Year is required"
            )
        }
    }
    shouldEnableAddButton()
}

fun validateDirector() {
    if (director.value.trim().isNotEmpty()) {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isDirectorValid = true,
                directorErrMsg = ""
            )
        }
    } else {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isDirectorValid = false,
                directorErrMsg = "Director is required"
            )
        }
    }
    shouldEnableAddButton()
}

fun validateActors() {
    if (actors.value.trim().isNotEmpty()) {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isActorsValid = true,
                actorsErrMsg = ""
            )
        }
    } else {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isActorsValid = false,
                actorsErrMsg = "Actors is required"
            )
        }
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
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isRatingValid = true,
                ratingErrMsg = ""
            )
        }
    } else {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                isRatingValid = false,
                ratingErrMsg = "Rating is required and must be valid decimal format."
            )
        }
    }

    shouldEnableAddButton()
}

fun validateGenres(){
    if(selectableGenreItems.filter { item -> item.isSelected }.isEmpty()) {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                genreErrMsg = "Genre is required"
            )
        }
    } else {
        _addMovieValidationState.update { currentState ->
            currentState.copy(
                genreErrMsg = ""
            )
        }
    }
    shouldEnableAddButton()
}


 */

