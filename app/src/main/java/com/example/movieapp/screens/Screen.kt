package com.example.movieapp.screens

sealed class Screen (val route: String) {
    object MainScreen: Screen("home")
    object DetailScreen: Screen ("details")
    object FavoritesScreen: Screen("favorites")
}