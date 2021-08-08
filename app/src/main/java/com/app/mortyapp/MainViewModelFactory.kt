package com.app.mortyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mortyapp.Model.CharacterRepository

class MainViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val remoteDataSource = CharacterRemoteDataSource()
        val repository = CharacterRepository(remoteDataSource)

        return MainViewModel(repository) as T
    }
}