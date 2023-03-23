package com.example.movieappmad23.viewmodels

data class AddMovieValidationState(
    val isTitleValid: Boolean = true,
    val titleErrMsg: String = "",
    val isYearValid: Boolean = true,
    val yearErrMsg: String = "",
    val isDirectorValid: Boolean = true,
    val directorErrMsg: String = "",
    val isActorsValid: Boolean = true,
    val actorsErrMsg: String = "",
    val isRatingValid: Boolean = true,
    val ratingErrMsg: String = "",
    val genreErrMsg: String = "",
)
