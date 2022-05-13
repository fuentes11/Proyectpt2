package com.example.ferreteria_mi_casa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ferreteria_mi_casa.products.CartAdapter
import com.example.ferreteria_mi_casa.products.model.CartList
import com.example.ferreteria_mi_casa.products.model.ProductData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    private lateinit var productList: ArrayList<ProductData>
    private lateinit var mAdapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        buildlist()
        buyItem()

        btn_limpiar_carrito.setOnClickListener {
            val appSharedPrefs = this.getSharedPreferences(
                getString(R.string.lista_carrito), Context.MODE_PRIVATE
            )
            val prefsEditor = appSharedPrefs.edit()
            prefsEditor.putString(getString(R.string.lista_carrito), "")
            prefsEditor.apply()
            buildlist()
        }
    }

    private fun buildlist() {
        productList = ArrayList()
        var totalPrice = 0.0
        val appSharedPrefs = this.getSharedPreferences(
            getString(R.string.lista_carrito), Context.MODE_PRIVATE
        )
        val json: String? = appSharedPrefs.getString(getString(R.string.lista_carrito), "")
        if (json != null && json.isNotBlank()) {
            val obj = Gson().fromJson(json, CartList::class.java)
            println("Si hay algo ${obj.items}")
            productList = obj.items

            obj.items.forEach {  product ->
                val price : Double? = product.price?.toDouble()
                price?.let { peniwuais ->
                    totalPrice += peniwuais
                }
            }
        }
        recyclerCart.layoutManager = LinearLayoutManager(this)
        recyclerCart.setHasFixedSize(true)
        mAdapter = CartAdapter(this, productList)
        recyclerCart.adapter = mAdapter
        txttotala.text = totalPrice.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.myacc -> startActivity(Intent(this, Mainbranch::class.java))
            R.id.Branchs -> startActivity(Intent(this, Mainbranch::class.java))
            R.id.About -> startActivity(Intent(this, Mainbranch::class.java))
            R.id.products -> startActivity(Intent(this, MainProducts::class.java))
        }
        return super.onOptionsItemSelected(item)

    }

    private fun buyItem() {
        btn_comprar_carrito.setOnClickListener {
            val appSharedPrefs = this.getSharedPreferences(
                getString(R.string.lista_carrito), Context.MODE_PRIVATE
            )
            val json: String? = appSharedPrefs.getString(getString(R.string.lista_carrito), "")
            if (json != null && json.isNotBlank()) {
                val intent = Intent(this, OrderReady::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "El carrito esta vacio", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}