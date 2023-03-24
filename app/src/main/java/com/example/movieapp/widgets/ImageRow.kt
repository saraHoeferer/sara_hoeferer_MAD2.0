package com.example.movieapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageRow(movieImages: List<String>) {
    Row(){
        Divider(
            color = Color.LightGray,
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Movie Images",
            fontSize = 28.sp,
        )
    }
    ImageSlider(movieImages = movieImages)

}