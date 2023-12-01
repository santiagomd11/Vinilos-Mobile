package com.example.vinilos.ui.collector_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter

class CollectorDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _collectorDetails = MutableLiveData<Collector>()
    val collectorDetails: LiveData<Collector> = _collectorDetails

    fun fetchCollectorDetails(collectorId: Int) {
        NetworkServiceAdapter.getInstance(getApplication()).getCollectorById(collectorId,
            onComplete = { collector ->
                _collectorDetails.postValue(collector)
            },
            onError = { error ->
                Log.e("CollectorDetailViewModel", "Error fetching collector details", error)
            }
        )
    }
}
