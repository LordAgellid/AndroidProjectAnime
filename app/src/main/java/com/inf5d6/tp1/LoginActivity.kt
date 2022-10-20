package com.inf5d6.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]


        val btnLogin = this.findViewById<Button>(R.id.login)
        btnLogin.setOnClickListener {
            val username = this.findViewById<EditText>(R.id.username).text.toString()
            val password = this.findViewById<EditText>(R.id.password).text.toString()

            loginViewModel.username = username
            loginViewModel.password = password
            loginViewModel.getToken()
        }
        loginViewModel.isValid.observe(this) {
            if(it == true) {
                println(MainActivity.TOKEN)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}