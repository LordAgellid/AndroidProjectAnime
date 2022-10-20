package com.inf5d6.tp1.ui.favorites

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.TvShow

class FavoritesRepository(private val application: Application) {
    fun getFavoritesTvShows(favoritesTvShows: MutableLiveData<MutableList<TvShow>>) {
        val queue = Volley.newRequestQueue(application)
        val url ="${MainActivity.SRVURL}/favorites"

        val r = object: JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                println(it)
                val arrayTvShows = Gson().fromJson(it.toString(), Array<TvShow>::class.java)
                favoritesTvShows.postValue(arrayTvShows.toMutableList())
            },
            {
                println("ERROR")
                println(it)
            }
        )
        {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                headers.put("Authorization", "Bearer ${MainActivity.TOKEN}")
                return headers
            }
        }

        queue.add(r)
    }
}