package com.inf5d6.tp1

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginRepository(private val application: Application) {
    fun login(username: String, password: String, isValid: MutableLiveData<Boolean>) {
        val queue = Volley.newRequestQueue(application)
        val url ="${MainActivity.SRVURL}/auth/token"
        val body = JSONObject()

        body.put("username", username)
        body.put("password", password)

        val r = JsonObjectRequest(
            Request.Method.POST,
            url,
            body,
            {
                println(it)
                MainActivity.TOKEN = it.getString("token")
                isValid.value = true
            },
            {
                val jo = JSONObject(String(it.networkResponse.data))
                val messageErreurServer = jo.getString("message")
                println(messageErreurServer)

                val messageErreur = "Erreur de connexion! Veuillez vérifier votre nom d'utilisateur & votre mot de passe."
                Toast.makeText(application, messageErreur, Toast.LENGTH_LONG).show()
                isValid.value = false
            }
        )
        queue.add(r)
    }
}