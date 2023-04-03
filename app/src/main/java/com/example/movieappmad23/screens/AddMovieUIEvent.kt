package com.example.movieappmad23.screens

import com.example.movieappmad23.models.Genre

sealed class AddMovieUIEvent{
    data class TitleChanged(val title: String): AddMovieUIEvent()
    data class YearChanged(val year: String): AddMovieUIEvent()
    data class GenresChanged(val genres: List<Genre>): AddMovieUIEvent()
    data class DirectorChanged(val director: String): AddMovieUIEvent()
    data class ActorsChanged(val actors: String): AddMovieUIEvent()
    data class PlotChanged(val plot: String): AddMovieUIEvent()
    data class RatingChanged(val rating: String): AddMovieUIEvent()
    object submit: AddMovieUIEvent()
}
