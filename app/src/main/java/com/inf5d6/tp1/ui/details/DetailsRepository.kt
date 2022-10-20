package com.inf5d6.tp1.ui.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.inf5d6.tp1.MainActivity
import com.inf5d6.tp1.models.DetailsTvShow
import com.inf5d6.tp1.models.TvShow

class DetailsRepository(private val application: Application) {
    fun getDetails(details: MutableLiveData<DetailsTvShow>, idTvShow: Int) {
        val queue = Volley.newRequestQueue(application)
        val url = "${MainActivity.SRVURL}/tvshow?tvshowId=${idTvShow}"

        val r = StringRequest(
            Request.Method.GET,
            url,
            {
                val detailObject = Gson().fromJson(it, DetailsTvShow::class.java)
                details.value = detailObject
            },
            {
                println("ERROR")
                println(it)
            }
        )
        queue.add(r)
    }

    fun checkFavorite(idTvShow: Int, isFavorite: MutableLiveData<Boolean>) {
        val queue = Volley.newRequestQueue(application)
        val url = "${MainActivity.SRVURL}/favorite?tvshowId=${idTvShow}"

        val r = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                println(it)
                isFavorite.value = it.get("isFavorite") as Boolean
            },
            {
                print("Error")
                println(it)
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                headers.put("Authorization", "Bearer ${MainActivity.TOKEN}")
                return headers
            }
        }

        queue.add(r)
    }

    fun addFavorite(idTvShow: Int) {
        val queue = Volley.newRequestQueue(application)
        val url = "${MainActivity.SRVURL}/favorite?tvshowId=${idTvShow}"

        val r = object : StringRequest(
            Request.Method.POST,
            url,
            {
                print("SUCESS")
            },
            {
                print("error")
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                headers.put("Authorization", "Bearer ${MainActivity.TOKEN}")
                return headers
            }
        }
        queue.add(r)
    }
    fun delFavorite(idTvShow: Int) {
        val queue = Volley.newRequestQueue(application)
        val url = "${MainActivity.SRVURL}/favorite?tvshowId=${idTvShow}"

        val r = object : StringRequest(
            Request.Method.DELETE,
            url,
            {
                print("SUCESS")
            },
            {
                print("error")
            }
        ) {
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
