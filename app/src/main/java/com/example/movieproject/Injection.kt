package com.example.movieproject

import com.example.movieproject.data.data.remote.ApiManager
import com.example.movieproject.data.data.remote.MovieApi
import com.example.movieproject.data.data.repository.Repository
import com.example.movieproject.data.data.repository.movie_repository.MovieRepository
import com.example.movieproject.presentation.ViewModelFactory

object Injection {
    val movieApi: ApiManager by lazy { MovieApi() }
    val repository:Repository by lazy { MovieRepository(movieApi) }
    val viewModelFactory:ViewModelFactory by lazy {
        ViewModelFactory(repository)
    }
}