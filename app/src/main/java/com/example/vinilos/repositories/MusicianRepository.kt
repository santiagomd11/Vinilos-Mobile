package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Musician
import com.example.vinilos.network.NetworkServiceAdapter

class MusicianRepository(val application: Application){
    suspend fun refreshData() : List<Musician> {
        return NetworkServiceAdapter.getInstance(application).getMusicians()
    }
}