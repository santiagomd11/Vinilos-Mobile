package com.example.vinilos.ui.artist_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtistDetailViewModel : ViewModel(){


    // IMPLEMENTAR LOGICA
    private val _artistTitleText = MutableLiveData<String>().apply {
        value = "Fragmento Artistas"
    }

    private val _artistDescriptionText = MutableLiveData<String>().apply {
        value = "Fragmento Artistas"
    }
    val artistTitle: LiveData<String> = _artistTitleText

    val artistDescription: LiveData<String> = _artistDescriptionText

}