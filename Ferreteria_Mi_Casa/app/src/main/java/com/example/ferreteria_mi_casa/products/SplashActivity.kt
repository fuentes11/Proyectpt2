package com.example.ferreteria_mi_casa.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ferreteria_mi_casa.Home
import com.example.ferreteria_mi_casa.MainActivity
import com.example.ferreteria_mi_casa.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}