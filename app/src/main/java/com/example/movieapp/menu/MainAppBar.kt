package com.example.movieapp.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.screens.Screen

@Composable
fun MainAppBar(navController: NavController){
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
            .padding(15.dp)
    ) {
        // Add a Column
        Column {
            // Add Text
            Text(
                text = "Movies",
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
                    DropdownMenuItem(onClick = { expanded = false; navController.navigate(Screen.FavoritesScreen.route) }) {
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