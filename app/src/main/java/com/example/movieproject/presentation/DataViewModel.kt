package com.example.movieproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.data.data.repository.Repository

class DataViewModel(val repository: Repository): ViewModel() {
    val liveData = repository.liveData as LiveData<ArrayList<MovieData>>
    val selectedData = repository.selectedData as LiveData<MovieData>

    fun getData() {
        repository.getData()
    }
}