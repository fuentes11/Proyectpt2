package com.example.ferreteria_mi_casa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ferreteria_mi_casa.products.Adapter
import com.example.ferreteria_mi_casa.products.model.ProductData
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_products.*
import kotlinx.android.synthetic.main.activity_new.*

class MainProducts : AppCompatActivity() {
    lateinit var mDataBase: DatabaseReference
    private lateinit var ProductList:ArrayList<ProductData>
    private lateinit var mAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_products)
        /**initialized*/
        ProductList = ArrayList()
        mAdapter = Adapter(this,ProductList)
        recyclerproducts.layoutManager = LinearLayoutManager(this)
        recyclerproducts.setHasFixedSize(true)
        // recyclerAnimals.adapter = mAdapter
        /**getData firebase*/
        getProductsData()



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart ->  startActivity(Intent(this, CartActivity::class.java))
            R.id.myacc ->   startActivity(Intent(this, Mainbranch::class.java))
            R.id.Branchs ->  startActivity(Intent(this, Mainbranch::class.java))
            R.id.About ->  startActivity(Intent(this, Mainbranch::class.java))
            R.id.cart ->  startActivity(Intent(this, CartActivity::class.java))
        }
        return super.onOptionsItemSelected(item)

    }

    /**ok now create new activity*/


    private fun getProductsData() {

        mDataBase = FirebaseDatabase.getInstance().getReference("Drink")
        mDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (animalSnapshot in snapshot.children){
                        val animal = animalSnapshot.getValue(ProductData::class.java)
                        ProductList.add(animal!!)
                    }
                    recyclerproducts.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainProducts,
                    error.message, Toast.LENGTH_SHORT).show()
            }

        })


    }





}