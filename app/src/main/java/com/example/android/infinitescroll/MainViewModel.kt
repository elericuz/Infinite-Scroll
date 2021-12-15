package com.example.android.infinitescroll

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.infinitescroll.api.RickAndMortyApi
import com.example.android.infinitescroll.models.Character
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var _characters = MutableLiveData<Character>()
    val characters: LiveData<Character> get() = _characters

    init {
        viewModelScope.launch {
            try {
                _characters.value = RickAndMortyApi.retrofitService.getCharacter().await()
            } catch(e: Exception) {
                Log.e(TAG, e.localizedMessage)
            }
        }
    }

    companion object {
        val TAG = "infiniteScroll"
    }
}