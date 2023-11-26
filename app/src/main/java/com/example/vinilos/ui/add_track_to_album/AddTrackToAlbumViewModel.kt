package com.example.vinilos.ui.add_track_to_album

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AddTrackToAlbumViewModel(application: Application) : AndroidViewModel(application)  {
    private val _albumDetails = MutableLiveData<Album>()
    private var albumId = 0
    val albumDetails: LiveData<Album> = _albumDetails

    fun fetchAlbumDetails(albumId: Int) {
        this.albumId = albumId
        NetworkServiceAdapter.getInstance(getApplication()).getAlbumById(albumId,
            onComplete = { album ->
                _albumDetails.postValue(album)
            },
            onError = { error ->
                Log.e("AddTrackToAlbumViewModel", "Error fetching album details", error)
            }
        )
    }
}