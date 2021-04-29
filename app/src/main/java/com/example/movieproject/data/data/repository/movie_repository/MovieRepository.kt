package com.example.movieproject.data.data.repository.movie_repository

import androidx.lifecycle.MutableLiveData
import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.data.data.remote.ApiManager
import com.example.movieproject.data.data.repository.Repository

class MovieRepository(val apiManager: ApiManager) : Repository(apiManager) {
    override var liveData = MutableLiveData<ArrayList<MovieData>>()
    override var selectedData = MutableLiveData<MovieData>(null)

    override fun getData() {
        apiManager.getData {
            liveData.value = it
        }
    }
}