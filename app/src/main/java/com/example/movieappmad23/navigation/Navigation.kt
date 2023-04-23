package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.factory.AddMovieViewModelFactory
import com.example.movieappmad23.factory.DetailViewModelFactory
import com.example.movieappmad23.factory.FavoriteViewModelFactory
import com.example.movieappmad23.factory.HomeViewModelFactory
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.screens.*
import com.example.movieappmad23.viewmodels.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // inside a composable
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val homeFactory = HomeViewModelFactory(repository)
    val favoriteFactory = FavoriteViewModelFactory(repository)
    val addMovieFactory = AddMovieViewModelFactory(repository)
    val detailFactory = DetailViewModelFactory(repository)
    val homeViewModel: HomeViewModel = viewModel(factory = homeFactory)
    val favoriteViewModel: FavoriteViewModel = viewModel(factory = favoriteFactory)
    val addMovieViewModel: AddMovieViewModel = viewModel(factory = addMovieFactory)
    val detailViewModel: DetailViewModel = viewModel(factory = detailFactory)

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, moviesViewModel = homeViewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, moviesViewModel = favoriteViewModel)
        }
        
        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, moviesViewModel = addMovieViewModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController,
                moviesViewModel = detailViewModel,
                movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY)) // get the argument from navhost that will be passed
        }
    }
}