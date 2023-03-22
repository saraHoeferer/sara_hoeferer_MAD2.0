package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.models.Movie
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.models.getMovies


// Main function to start the App
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Column with the Top Bar Menu and the List of Movies
                    Column {
                        TopAppBar()
                        MovieList(movies = getMovies())
                    }
                }
            }
        }
    }
}

// function used for the Top Bar Menu
@Composable
fun TopAppBar() {
    // the state of the dropdown menu is stored as using a remember state
    var expanded by remember {
        // by default the menu is collapsed
        mutableStateOf(false)
    }

    // Add a Row
    Row(
        modifier = Modifier
            // Modifiers to add color, fill up the max width of the screen and add some padding
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        // Add a Column
        Column {
            // Add Text
            Text(text = "Movies",
                color = Color.White,
                fontSize = 20.sp
            )
        }
        // Add another Column
        Column(
            modifier = Modifier
                // align the content of the Column on the right side of the Menu Bar
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            // Add a Box so the dropdown Menu and the Icon are linked
            Box {
                // Add the icon to toggle the dropdown menu
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "dropdownMenu",
                    tint = Color.White,
                    modifier = Modifier
                        // Add a modifier so when the icon is clicked the dropdown menu gets expanded
                        .clickable(onClick = { expanded = true })
                )
                // Add the dropdown menu
                DropdownMenu(
                    // set the expanded variable to whatever is chosen by the user
                    expanded = expanded,
                    // on dismiss collapse the menu again
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(color = Color.White)
                ) {
                    // Add a dropdown menu item that when clicked on collapses the menu
                    DropdownMenuItem(onClick = { expanded = false }) {
                        // Add a Row
                        Row {
                            // A heart icon
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "favourites",
                                tint = Color.Black,
                            )
                            // And Text
                            Text(
                                text = "Favorites",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// function to make a row containing all the info's for one movie
@Composable
fun MovieRow(movie: Movie) {
    // the state of the arrow icon to toggle the details is stored in a remember state
    var arrow by remember {
        // by default the arrow is turned upwards
        mutableStateOf(Icons.Default.KeyboardArrowUp)
    }

    // the state of the details (visible or not) is stored in a remember state
    var visible by remember {
        // by default the details are not visible
        mutableStateOf(false)
    }

    // Add a Card with some modifiers
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        // Add a column
        Column {
            // Add a Box to combine the Image an the Heart Icon
            Box(
                modifier = Modifier
                    .height(150.dp)
            ) {
                // Load the Image via AsyncImage using the Coil library
                AsyncImage(
                    // make a model using the first images url found in the images array of a movie object
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    // a description, define the scale and add some modifiers
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                // Use another box to align the Icon on the Top Right
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    // Add the heart Icon
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
            // Add a Row with padding, a certain height and vertical alignment
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 3.dp)
                    .height(35.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Add a Column
                Column {
                    // To show the movie title
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
                // Add another column and align it on the right side
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    // Add the arrow icon
                    Icon(imageVector = arrow,
                        contentDescription = "arrow",
                        modifier = Modifier
                            // Add a clickable event so the Image of the Icon changes when clicked on
                            .clickable(onClick = {
                                arrow = if (arrow == Icons.Default.KeyboardArrowUp) {
                                    Icons.Default.KeyboardArrowDown
                                } else {
                                    Icons.Default.KeyboardArrowUp
                                }
                                // And change the state of editable so the Details are shown/hidden when clicked on
                                visible = !visible
                            }
                            )
                    )
                }
            }
            // Add a Row
            Row {
                // Add a Animated Visibility method to show/hide the details of a movie
                AnimatedVisibility(visible = visible) {
                    // Add a column
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 15.dp)
                    ) {
                        // Add text to show the director, release year, genre, actors and rating
                        Text(text = "Director: ${movie.director}")
                        Text(text = "Released: ${movie.year}")
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                        // Add a Divider (straight line) before the plot
                        Divider(
                            color = Color.LightGray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        // Add text for the plot of the movie
                        Text(
                            text = "Plot: ${movie.plot}",
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        )
                    }
                }
            }

        }
    }
}

// Function used to display all the movies in a column
@Composable
fun MovieList(movies: List<Movie>) {
    // Use LazyColumn to only load visible movies
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie)
        }
    }
}

// Function used to show App in Preview Window
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        Column {
            TopAppBar()
            MovieList(movies = getMovies())
        }
    }
}