package com.example.movieapp.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest

@Composable
fun LoadAsyncImage(image: String){
    coil.compose.AsyncImage(
        // make a model using the first images url found in the images array of a movie object
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        // a description, define the scale and add some modifiers
        contentDescription = image,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}