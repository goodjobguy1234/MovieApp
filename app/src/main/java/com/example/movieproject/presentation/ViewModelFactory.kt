@file:Suppress("UNCHECKED_CAST")

package com.example.movieproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieproject.data.data.repository.Repository

class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) return DataViewModel(repository) as T
        else throw IllegalArgumentException("Unknown ViewModel Class")
    }
}