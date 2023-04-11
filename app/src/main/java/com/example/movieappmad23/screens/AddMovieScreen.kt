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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.MoviesViewModel
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun AddMovieScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
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
            checkEmptyString = { input -> moviesViewModel.isNotEmpty(input) },
            checkEmptyFloat = { input -> moviesViewModel.floatIsNotEmpty(input) },
            checkEmptyGenre = { input -> moviesViewModel.genreNotEmpty(input) },
            addMovie = { title, year, genre, director, actors, plot, rating ->
                moviesViewModel.addMovie(
                    title = title,
                    year = year,
                    genres = genre,
                    director = director,
                    actors = actors,
                    plot = plot,
                    rating = rating
                )
            },
            navigateBack = { navController.popBackStack() })
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)

    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    checkEmptyString: (String) -> Boolean,
    checkEmptyFloat: (String) -> Boolean,
    checkEmptyGenre: (List<ListItemSelectable>) -> Boolean,
    addMovie: (String, String, List<ListItemSelectable>, String, String, String, String) -> Unit,
    navigateBack: () -> Unit = {}
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
            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

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

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(false)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = checkEmptyString(title)
            )
            if (checkEmptyString(title)) {
                ErrorText(text = "Title is required")
            }

            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { year = it },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = checkEmptyString(year)
            )
            if (checkEmptyString(year)) {
                ErrorText(text = "Year is required")
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)
            ) {
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            if (checkEmptyGenre(genreItems)) {
                ErrorText(text = "At least one genre must be selected")
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { director = it },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = checkEmptyString(director)
            )
            if (checkEmptyString(director)) {
                ErrorText(text = "Director is required")
            }

            OutlinedTextField(
                value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { actors = it },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = checkEmptyString(actors)
            )
            if (checkEmptyString(actors)) {
                ErrorText(text = "Actors are required")
            }

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = {
                    Text(
                        textAlign = TextAlign.Start,
                        text = stringResource(R.string.enter_plot)
                    )
                },
                isError = false
            )

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    rating = if (it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = checkEmptyFloat(rating)
            )
            if (checkEmptyFloat(rating)) {
                ErrorText(text = "Rating is required (e.g. 1.2, 4, etc.)")
            }

            if (!checkEmptyString(title) && !checkEmptyString(year) && checkEmptyGenre(genreItems) && checkEmptyString(director) && checkEmptyString(actors) && checkEmptyFloat(rating)){
                isEnabledSaveButton = true
            }

            Button(
                enabled = isEnabledSaveButton,
                onClick = {
                    addMovie(title, year, genreItems, director, actors, plot, rating)
                    navigateBack()
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}