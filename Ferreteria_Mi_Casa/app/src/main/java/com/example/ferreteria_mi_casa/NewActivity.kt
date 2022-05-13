package com.example.ferreteria_mi_casa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ferreteria_mi_casa.products.model.CartList
import com.example.ferreteria_mi_casa.products.model.ProductData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        /**get Data*/
        val productIntent = intent
        val productKey = productIntent.getStringExtra("key")
        val productName = productIntent.getStringExtra("name")
        val productprice = productIntent.getStringExtra("price")
        val productImage = productIntent.getStringExtra("image")


        /**call text and images*/
        name.text = productName
        price.text = productprice
        image.loadImage(productImage, getProgessDrawable(this))
        btn_addtocart.setOnClickListener {
            val appSharedPrefs = this.getSharedPreferences(
                getString(R.string.lista_carrito), Context.MODE_PRIVATE
            )
            val json: String? = appSharedPrefs.getString(getString(R.string.lista_carrito), "")
            if (json != null && json.isNotBlank()) {
                val obj = Gson().fromJson(json, CartList::class.java)
                obj.items.add(ProductData(productKey, productName, productprice, productImage))
                val texto = Gson().toJson(obj)
                val prefsEditor = appSharedPrefs.edit()
                prefsEditor.putString(getString(R.string.lista_carrito), texto)
                prefsEditor.apply()
            } else {
                val obj = CartList()
                obj.items =
                    arrayListOf(ProductData(productKey, productName, productprice, productImage))
                val texto = Gson().toJson(obj)
                val prefsEditor = appSharedPrefs.edit()
                prefsEditor.putString(getString(R.string.lista_carrito), texto)
                prefsEditor.apply()
            }
            Toast.makeText(applicationContext, "Añadido al carrito", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart -> startActivity(Intent(this, CartActivity::class.java))
            R.id.myacc -> startActivity(Intent(this, Mainbranch::class.java))
            R.id.Branchs -> startActivity(Intent(this, Mainbranch::class.java))
            R.id.About -> startActivity(Intent(this, Mainbranch::class.java))
            R.id.products -> startActivity(Intent(this, MainProducts::class.java))
        }
        return super.onOptionsItemSelected(item)

    }
    /**ok now run it */
}