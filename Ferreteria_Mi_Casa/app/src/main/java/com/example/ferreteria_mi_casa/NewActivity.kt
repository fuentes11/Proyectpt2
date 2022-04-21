package com.example.ferreteria_mi_casa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
    /**ok now run it */
}