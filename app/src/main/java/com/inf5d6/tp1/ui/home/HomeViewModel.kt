package com.inf5d6.tp1.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inf5d6.tp1.models.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val app: Application) : AndroidViewModel(app) {
    public val tvShows: MutableLiveData<MutableList<TvShow>> =  MutableLiveData(mutableListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val homeRepository = HomeRepository(getApplication())
            homeRepository.getTvShows(tvShows)
        }
    }



}