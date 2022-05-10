package com.example.ferreteria_mi_casa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        buyItem()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.myacc ->   startActivity(Intent(this, Mainbranch::class.java))
            R.id.Branchs ->  startActivity(Intent(this, Mainbranch::class.java))
            R.id.About ->  startActivity(Intent(this, Mainbranch::class.java))
            R.id.products ->  startActivity(Intent(this, MainProducts::class.java))
        }
        return super.onOptionsItemSelected(item)

    }

    private fun buyItem() {
        btn_addtocart.setOnClickListener {
            val intent  = Intent(this, OrderReady::class.java)
            startActivity(intent)
        }
    }
}