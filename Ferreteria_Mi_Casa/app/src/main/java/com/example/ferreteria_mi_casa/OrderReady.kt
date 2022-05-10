package com.example.ferreteria_mi_casa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class OrderReady : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sold_activity)
        backtoHome()
    }


    private fun backtoHome() {
        btn_addtocart.setOnClickListener {
            val intent  = Intent(this, MainProducts::class.java)
            startActivity(intent)
        }
    }
}