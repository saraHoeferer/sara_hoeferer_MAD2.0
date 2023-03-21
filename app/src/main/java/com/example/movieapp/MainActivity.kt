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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.ui.theme.MovieAppTheme

data class Movies(val name: String, val director: String, val relaseYear: Int, val genre: String, val actors: String, val rating: Double, val plot: String, val imageUrl: String);

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
                    var movies = listOf<Movies>(
                        Movies("Avatar 2",
                            "James Cameron",
                            2023,
                            "Action, Adventure, Fantasy",
                            "Sam Worthington, Zoe Saldena, Sigoamey Weaver, Stephen Lang",
                            7.9,
                            "Jake Sully lives with his newfound family formed on the extrasolar moon Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na'vi race to protect their home.",
                            "https://images.wallpapersden.com/image/download/avatar-2-the-way-of-water-movie-poster_bWxsaWeUmZqaraWkpJRmbmdlrWZlbWU.jpg"
                        ),
                        Movies("Being John Malkovich",
                            "Spike Jonze",
                            1999,
                            "Comedy, Drama, Fantasy",
                            "John Cusack, Catherine Keener, Cameron Diaz, John Malkovich",
                            7.8,
                            "A puppeteer discovers a portal that leads literally into the head of movie star John Malkovich.",
                            "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/51XivNSv1oL._AC_UY1000_.jpg"
                        ),
                        Movies("Clockwork Orange",
                            "Stanley Kubrick",
                            1971,
                            "Crime, Sci-Fi",
                            "Malcolm McDowell, Patrick Magee, Michael Bates, Warren Clarke",
                            8.3,
                            "In the future, a sadistic gang leader is imprisoned and volunteers for a conduct-aversion experiment, but it doesn't go as planned.",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaqugT7C4ez-MfvfzzUdjPxxr1urvyHlWlxQ&usqp=CAU"
                        ),
                        Movies("The Cat in the Hat",
                            "Bo Welch",
                            2003,
                            "Adventure, Comedy, Family",
                            "Mike Myers, Spencer Bresiln, Dakota Fanning",
                            4.0,
                            "Two bored children have their lives turned upside down when a talking cat comes to visit them.",
                            "https://static.wikia.nocookie.net/soundeffects/images/9/9c/The_Cat_in_the_Hat_2003_Poster.png/revision/latest/scale-to-width-down/485?cb=20151206185937"
                        )
                    )
                    Column() {
                        TopAppBar()
                        MovieList(movies = movies)
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
fun MovieRow(movie: Movies){
    var arrow by remember {
        mutableStateOf(Icons.Default.KeyboardArrowUp)
    }

    var editable by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 5.dp
    ) {
       Column(Modifier
           .padding(5.dp)
       ) {
            Box(modifier = Modifier
                .height(150.dp)
            )
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = movie.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                //Image(painter = painterResource(id = R.drawable.avatar), contentDescription = "Avatar2", contentScale = ContentScale.Crop)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Like", tint=MaterialTheme.colors.secondary)
                }
            }
           Row () {
               Column() {
                   Text(text = movie.name)
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
                       .padding(vertical = 10.dp)) {
                       Text(text = "Director: ${movie.director}")
                       Text(text = "Released: ${movie.relaseYear}")
                       Text(text = "Genre: ${movie.genre}")
                       Text(text = "Actors: ${movie.actors}")
                       Text(text = "Rating: ${movie.rating}")
                       Text(
                           text = "Plot: ${movie.plot}",
                           modifier = Modifier
                               .padding(horizontal = 30.dp, vertical = 15.dp)
                       )
                   }
               }
           }
            
        }
    }
}

@Composable
fun MovieList(movies: List<Movies>){
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
        var movies = listOf<Movies>(
            Movies("Avatar 2",
                "James Cameron",
                2023,
                "Action, Adventure, Fantasy",
                "Sam Worthington, Zoe Saldena, Sigoamey Weaver, Stephen Lang",
                7.9,
                "Jake Sully lives with his newfound family formed on the extrasolar moon Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na'vi race to protect their home.",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwallpapersden.com%2Favatar-2-the-way-of-water-movie-poster-wallpaper%2F1920x1080%2F&psig=AOvVaw2Wr11scEHqWhY44hezLLE0&ust=1679401323228000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCPjvjM2_6v0CFQAAAAAdAAAAABAJ"
            )
        )
        Column() {
            TopAppBar()
            MovieList(movies = movies)
        }
    }
}