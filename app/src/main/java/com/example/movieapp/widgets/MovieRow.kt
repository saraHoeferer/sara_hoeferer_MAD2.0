package com.example.movieapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.models.Movie

// function to make a row containing all the info's for one movie
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
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
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
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
                LoadAsyncImage(image = movie.images[0])
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
            AnimatedMovieDetails(visible = visible, movie = movie)
        }
    }
}