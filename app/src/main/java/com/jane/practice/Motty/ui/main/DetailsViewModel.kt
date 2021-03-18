package com.jane.practice.Motty.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jane.practice.Motty.data.RickAndMortyApi
import com.jane.practice.Motty.data.entities.MortyCharacter
import kotlinx.coroutines.launch

class DetailsViewModel(val id: Int) : ViewModel() {
    private val _character = MutableLiveData<MortyCharacter>()

    val character: LiveData<MortyCharacter>
        get() = _character

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch {
            try {
                _character.value = RickAndMortyApi.retrofitService.getCharacter(id)
            } catch (e: Exception) {
                Log.e("Jane", "GetCharacterbyId failed")
            }
        }
    }

}