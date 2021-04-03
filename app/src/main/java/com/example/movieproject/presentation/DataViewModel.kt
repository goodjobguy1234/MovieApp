package com.example.movieproject.presentation

import androidx.lifecycle.ViewModel
import com.example.movieproject.data.data.repository.Repository

class DataViewModel(val repository: Repository): ViewModel() {
    val liveData = repository.liveData
    fun getData(){
        repository.getData()
    }
}