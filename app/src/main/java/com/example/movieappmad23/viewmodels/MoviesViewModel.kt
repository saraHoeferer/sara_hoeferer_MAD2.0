package com.example.movieappmad23.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.common.Validator
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies
import com.example.movieappmad23.screens.AddMovieUIEvent
import com.example.movieappmad23.screens.AddMovieUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// inherit from ViewModel class
class MoviesViewModel: ViewModel() {
    private val _movieListState = MutableStateFlow(listOf<Movie>())
    val movieListState: StateFlow<List<Movie>> = _movieListState.asStateFlow()

    private var _movieUiState = mutableStateOf(AddMovieUiState())
    val movieUiState: State<AddMovieUiState> = _movieUiState

    val favoriteMovies: List<Movie>
        get() = _movieListState.value.filter { it.isFavorite }

    init {
        _movieListState.value = getMovies()
    }

    fun onEvent(event: AddMovieUIEvent){
        when(event){
            is AddMovieUIEvent.TitleChanged -> {
                _movieUiState.value.copy(title = event.title)
                val titleResult = Validator.validateMovieTitle(_movieUiState.value.title)
                if (titleResult.successful) _movieUiState.value.copy(titleErr = false) else _movieUiState.value.copy(titleErr = true)
            }

            is AddMovieUIEvent.YearChanged -> {
                _movieUiState.value = _movieUiState.value.copy(year = event.year)
            }

            is AddMovieUIEvent.DirectorChanged -> {
                _movieUiState.value = _movieUiState.value.copy(director = event.director)
            }

            is AddMovieUIEvent.ActorsChanged -> {
                _movieUiState.value = _movieUiState.value.copy(actors = event.actors)
            }

            is AddMovieUIEvent.PlotChanged -> {
                _movieUiState.value = _movieUiState.value.copy(plot = event.plot)
            }

            is AddMovieUIEvent.RatingChanged -> {
                _movieUiState.value = _movieUiState.value.copy(rating = event.rating)
            }

            is AddMovieUIEvent.GenresChanged -> {
                _movieUiState.value = _movieUiState.value.copy(genre = event.genres)
            }

            is AddMovieUIEvent.submit -> {
                validateInputs()
            }
        }
    }

    private fun validateInputs() {
        val titleResult = Validator.validateMovieTitle(_movieUiState.value.title)
        val yearResult = Validator.validateMovieYear(_movieUiState.value.year)
        val genreResult = Validator.validateMovieGenres(_movieUiState.value.genre)
        val directorResult = Validator.validateMovieDirector(_movieUiState.value.director)
        val actorsResult = Validator.validateMovieActors(_movieUiState.value.actors)
        val ratingResult = Validator.validateMovieRating(_movieUiState.value.rating)

        _movieUiState.value = _movieUiState.value.copy(
            titleErr = !titleResult.successful,
            yearErr = !yearResult.successful,
            genreErr = !genreResult.successful,
            directorErr = !directorResult.successful,
            actorsErr = !actorsResult.successful,
            ratingErr = !ratingResult.successful
        )

        val hasError = listOf(
            titleResult,
            yearResult,
            genreResult,
            directorResult,
            actorsResult,
            ratingResult
        ).any {
            !it.successful
        }

        viewModelScope.launch {
            if(!hasError){
                saveMovie()
            }
        }

    }

    fun updateFavoriteMovies(movie: Movie) = _movieListState.value.find { it.id == movie.id }?.let { movie ->
        movie.isFavorite = !movie.isFavorite
    }


    suspend fun saveMovie() {
        //itemsRepository.insertItem(itemUiState.toItem())
    }
}