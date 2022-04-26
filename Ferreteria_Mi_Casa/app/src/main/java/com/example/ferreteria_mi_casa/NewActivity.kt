package com.example.ferreteria_mi_casa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        /**get Data*/
        val ProductIntent = intent
        val ProductName = ProductIntent.getStringExtra("name")
        val Productprice = ProductIntent.getStringExtra("price")
        val ProductImage = ProductIntent.getStringExtra("image")


        /**call text and images*/
        name.text = ProductName
        price.text = Productprice
        image.loadImage(ProductImage, getProgessDrawable(this))


            btn_addtocart.setOnClickListener {
                Toast.makeText(applicationContext,"Añadido al carrito", Toast.LENGTH_SHORT).show()
                val intent  = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }


    }
    /**ok now run it */
}