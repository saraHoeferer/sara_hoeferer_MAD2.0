package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.viewmodels.MoviesViewModel
import com.example.movieappmad23.widgets.SimpleTextField
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun AddMovieScreen(
    navController: NavController,
    moviesViewModel: MoviesViewModel
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(
            Modifier.padding(padding),
            moviesViewModel = moviesViewModel,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel,
    navController: NavController
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        val state by moviesViewModel.addMovieValidationState.collectAsState()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            SimpleTextField(
                value = moviesViewModel.title.value,
                label = stringResource(R.string.enter_movie_title),
                isValid = state.isTitleValid,
                errMsg = state.titleErrMsg,
                onDone = { moviesViewModel.validateTitle() }
            ) { input ->
                moviesViewModel.title.value = input
                moviesViewModel.validateTitle()
            }

            SimpleTextField(
                value = moviesViewModel.year.value,
                label = stringResource(id = R.string.enter_movie_year),
                errMsg = state.yearErrMsg,
                isValid = state.isYearValid,
            ) {
                moviesViewModel.year.value = it
                moviesViewModel.validateYear()
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(moviesViewModel.selectableGenreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            moviesViewModel.selectGenre(genreItem)
                            moviesViewModel.validateGenres()
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = state.genreErrMsg,
                fontSize = 14.sp,
                color = MaterialTheme.colors.error
            )

            SimpleTextField(
                value = moviesViewModel.director.value,
                label = stringResource(R.string.enter_director),
                errMsg = state.directorErrMsg,
                isValid = state.isDirectorValid,
            ) {
                moviesViewModel.director.value = it
                moviesViewModel.validateDirector()
            }

            SimpleTextField(
                value = moviesViewModel.actors.value,
                label = stringResource(R.string.enter_actors),
                errMsg = state.actorsErrMsg,
                isValid = state.isActorsValid,
            ) {
                moviesViewModel.actors.value = it
                moviesViewModel.validateActors()
            }

            SimpleTextField(
                value = moviesViewModel.plot.value,
                label = stringResource(R.string.enter_plot),
                isValid = true,
                singleLine = false,
                modifier = Modifier.height(120.dp)
            ) {
                moviesViewModel.plot.value = it
            }

            SimpleTextField(
                value = moviesViewModel.rating.value.toString(),
                label = stringResource(R.string.enter_rating),
                keyboardType = KeyboardType.Decimal,
                errMsg = state.ratingErrMsg,
                isValid = state.isRatingValid,
            ) {
                moviesViewModel.rating.value = it
                moviesViewModel.validateRating()
            }

            Button(
                enabled = moviesViewModel.isEnabledSaveButton.value,
                onClick = {
                    moviesViewModel.addMovie()
                    navController.navigate(Screen.MainScreen.route)
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}
