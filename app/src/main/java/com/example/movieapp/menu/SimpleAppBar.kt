package com.example.movieapp.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SimpleAppBar(menuText: String, navController: NavController){
    // Add a Row
    Row(
        modifier = Modifier
            // Modifiers to add color, fill up the max width of the screen and add some padding
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        // Add a Column
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "arrowBack",
            tint = Color.White,
            modifier = Modifier
                // Add a modifier so when the icon is clicked the dropdown menu gets expanded
                .clickable(onClick = { navController.popBackStack()})
        )
        Text(
            text = menuText,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
    }
}