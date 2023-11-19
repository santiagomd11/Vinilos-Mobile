package com.example.vinilos.ui.album_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _albumDetails = MutableLiveData<Album>()
    val albumDetails: LiveData<Album> = _albumDetails

    fun fetchAlbumDetails(albumId: Int) {
        NetworkServiceAdapter.getInstance(getApplication()).getAlbumById(albumId,
            onComplete = { album ->
                _albumDetails.postValue(album)
            },
            onError = { error ->
                Log.e("AlbumDetailViewModel", "Error fetching album details", error)
            }
        )
    }
}