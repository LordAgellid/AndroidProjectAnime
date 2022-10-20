package com.inf5d6.tp1.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.TvShow

class HomeRepository(private val application: Application) {
    fun getTvShows(tvShows: MutableLiveData<MutableList<TvShow>>) {
        val queue = Volley.newRequestQueue(application)
        val url ="${MainActivity.SRVURL}/tvshows"

        val r = StringRequest(
            Request.Method.GET,
            url,
            {
                println(it)
                val arrayTvShows = Gson().fromJson(it, Array<TvShow>::class.java)
                tvShows.postValue(arrayTvShows.toMutableList())
            },
            {
                println("ERROR")
                println(it)
            }
        )
        queue.add(r)
    }
}