package com.example.movieproject.data.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.data.data.remote.ApiManager

abstract class Repository(apiManager: ApiManager) {
    abstract var liveData:MutableLiveData<ArrayList<MovieData>>
    abstract fun getData()
}