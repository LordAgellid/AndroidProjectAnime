package com.inf5d6.tp1.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inf5d6.tp1.models.DetailsTvShow
import com.inf5d6.tp1.ui.favorites.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(val app: Application, private val idTvShow: Int) : AndroidViewModel(app) {
    public val detailsTvShow: MutableLiveData<DetailsTvShow> = MutableLiveData()
    var isFavorite = MutableLiveData<Boolean>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val detailsRepository = DetailsRepository(getApplication())
            detailsRepository.getDetails(detailsTvShow, idTvShow)
            detailsRepository.checkFavorite(idTvShow, isFavorite)
        }
    }

    fun addFavorite(idTvShow : Int){
        val detailsRepository = DetailsRepository(getApplication())
        detailsRepository.addFavorite(idTvShow)
    }

    fun delFavorite(idTvShow : Int){
        val detailsRepository = DetailsRepository(getApplication())
        detailsRepository.delFavorite(idTvShow)
    }
}
