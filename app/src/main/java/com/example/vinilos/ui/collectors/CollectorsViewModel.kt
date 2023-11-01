package com.example.vinilos.ui.collectors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CollectorsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento Coleccionistas"
    }
    val text: LiveData<String> = _text
}