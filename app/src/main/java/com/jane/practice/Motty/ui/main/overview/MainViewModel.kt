package com.jane.practice.Motty.ui.main.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jane.practice.Motty.data.RickAndMortyApi
import com.jane.practice.Motty.data.entities.MortyCharacter
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _characaters = MutableLiveData<List<MortyCharacter>>()
    val characters: LiveData<List<MortyCharacter>>
        get() = _characaters
    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _characaters.value = RickAndMortyApi.retrofitService.getAllCharacters().results
            } catch (e: Exception) {
                _characaters.value = ArrayList()
            }
        }
    }
}