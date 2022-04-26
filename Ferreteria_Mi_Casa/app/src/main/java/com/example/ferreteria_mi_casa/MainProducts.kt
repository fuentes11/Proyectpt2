package com.example.ferreteria_mi_casa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ferreteria_mi_casa.products.Adapter
import com.example.ferreteria_mi_casa.products.model.ProductData
import com.google.firebase.database.*
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