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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.viewmodels.MoviesViewModel
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
            moviesViewModel = moviesViewModel
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            OutlinedTextField(
                value = moviesViewModel.title.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    moviesViewModel.title.value = it
                    moviesViewModel.validateTitle()
                },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = !moviesViewModel.isTitleValid.value
            )

            OutlinedTextField(
                value = moviesViewModel.year.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    moviesViewModel.year.value = it
                    moviesViewModel.validateYear()
                },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = !moviesViewModel.isYearValid.value
            )

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
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            OutlinedTextField(
                value = moviesViewModel.director.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    moviesViewModel.director.value = it
                    moviesViewModel.validateDirector()
                },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = !moviesViewModel.isDirectorValid.value
            )

            OutlinedTextField(
                value = moviesViewModel.actors.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    moviesViewModel.actors.value = it
                    moviesViewModel.validateActors()
                },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = !moviesViewModel.isActorsValid.value
            )

            OutlinedTextField(
                value = moviesViewModel.plot.value,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { moviesViewModel.plot.value = it },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) }
            )

            OutlinedTextField(
                value = moviesViewModel.rating.value,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    moviesViewModel.rating.value = it
                    moviesViewModel.validateRating()
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = !moviesViewModel.isRatingValid.value
            )

            Button(
                enabled = moviesViewModel.isEnabledSaveButton.value,
                onClick = { moviesViewModel.addMovie() }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}