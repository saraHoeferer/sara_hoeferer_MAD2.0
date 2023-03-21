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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
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
                    Column() {
                        TopAppBar()
                        MovieList(movies = getMovies())
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(){
    var expanded by remember {
        mutableStateOf(true)
    }
    Row(modifier = Modifier
        .background(color = MaterialTheme.colors.primary)
        .fillMaxWidth()
        .padding(5.dp)
    ){
        Column() {
            Text(text = "Movies", color = Color.White)
        }
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.End) {
            Box (){
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable(onClick = {expanded = true})
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(color = Color.White)
                ) {
                    DropdownMenuItem(onClick = { expanded = false }) {
                        Row() {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Like",
                                tint = Color.Black,
                            )
                            Text(text = "Favorites",
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

@Composable
fun MovieRow(movie: Movie){
    var arrow by remember {
        mutableStateOf(Icons.Default.KeyboardArrowUp)
    }

    var editable by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
       Column() {
            Box(modifier = Modifier
                .height(150.dp)
            )
            {
                 AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.images[0])
                            .crossfade(true)
                            .build(),
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    //Image(painter = painterResource(id = R.drawable.avatar), contentDescription = "Avatar2", contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
            }
           Row (modifier = Modifier
               .padding(horizontal = 8.dp, vertical = 3.dp)
               .height(35.dp),
               verticalAlignment = Alignment.CenterVertically
           ) {
               Column() {
                   Text(text = movie.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                   )
               }
               Column(modifier = Modifier
                   .fillMaxWidth(),
                   horizontalAlignment = Alignment.End
               ) {
                       Icon(imageVector = arrow,
                           contentDescription = "Arrow",
                           modifier = Modifier
                               .clickable(onClick = {
                                   arrow = if (arrow == Icons.Default.KeyboardArrowUp) {
                                       Icons.Default.KeyboardArrowDown
                                   } else {
                                       Icons.Default.KeyboardArrowUp
                                   }
                                   editable = !editable
                               }
                               )
                       )
               }
           }
           Row() {
               AnimatedVisibility(visible = editable) {
                   Column(modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 8.dp, vertical = 15.dp)) {
                       Text(text = "Director: ${movie.director}")
                       Text(text = "Released: ${movie.year}")
                       Text(text = "Genre: ${movie.genre}")
                       Text(text = "Actors: ${movie.actors}")
                       Text(text = "Rating: ${movie.rating}")
                       Divider(color = Color.LightGray, thickness = 2.dp, modifier = Modifier.padding(vertical = 10.dp))
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

@Composable
fun MovieList(movies: List<Movie>){
    LazyColumn(){
        items(movies){ movie ->
            MovieRow(movie = movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        var movies = getMovies()
        Column() {
            TopAppBar()
            MovieList(movies = movies)
        }
    }
}