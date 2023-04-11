package com.example.movieappmad23

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.movieappmad23.models.MoviesViewModel
import com.example.movieappmad23.navigation.Navigation
import com.example.movieappmad23.ui.theme.MovieAppMAD23Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MoviesViewModel by viewModels()
        setContent {
            MovieAppMAD23Theme {
                Navigation(moviesViewModel = viewModel)
            }
        }
    }

    override fun onStart () {
        super.onStart()
        Log.i("MainActivity", "onStart called.")
    }
}

