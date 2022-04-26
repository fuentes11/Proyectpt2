package com.example.ferreteria_mi_casa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        buyItem()
    }

    private fun buyItem() {
        btn_addtocart.setOnClickListener {
            val intent  = Intent(this, OrderReady::class.java)
            startActivity(intent)
        }
    }
}