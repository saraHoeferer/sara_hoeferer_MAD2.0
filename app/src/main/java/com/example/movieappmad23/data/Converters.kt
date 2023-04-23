package com.example.movieappmad23.data

import androidx.room.TypeConverter
import com.example.movieappmad23.models.Genre

class Converters {
    @TypeConverter
    fun genreListToString(genre: List<Genre>): String{
        return genre.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun stringListToString(images: List<String>): String {
        return if (images.isNullOrEmpty()){
            "https://st4.depositphotos.com/14953852/24787/v/600/depositphotos_247872612-stock-illustration-no-image-available-icon-vector.jpg"
        } else {
            images.joinToString { "," }
        }
    }

    @TypeConverter
    fun stringToGenreList(genre: String): List<Genre> {
        return genre.split(",").map { Genre.valueOf(it) }
    }

    @TypeConverter
    fun stringToStringList(images: String): List<String> {
        return images.split(",").map { it.trim() }
    }


}