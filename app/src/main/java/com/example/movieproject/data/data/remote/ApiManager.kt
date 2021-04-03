package com.example.movieproject.data.data.remote

import com.example.movieproject.data.data.entity.MovieData

interface ApiManager {
    fun getData(callback: (ArrayList<MovieData>) -> Unit)
}