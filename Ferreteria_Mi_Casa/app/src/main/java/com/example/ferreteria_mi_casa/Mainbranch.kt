package com.example.ferreteria_mi_casa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ferreteria_mi_casa.R
import com.example.ferreteria_mi_casa.branchs.BranchAdapter
import com.example.ferreteria_mi_casa.branchs.model2.BranchData
import com.example.ferreteria_mi_casa.branchs.model2.branchDb
import com.example.ferreteria_mi_casa.products.Adapter
import com.example.ferreteria_mi_casa.products.model.ProductData
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main_products.*
import kotlinx.android.synthetic.main.activity_mainbranch.*

class Mainbranch : AppCompatActivity() {
    lateinit var mmDataBase: DatabaseReference
    private lateinit var BranchList:ArrayList<BranchData>
    private lateinit var mmAdapter: BranchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainbranch)
        /**initialized*/
        BranchList = ArrayList()
        mmAdapter = BranchAdapter(this, BranchList )
        recyclerbranch.layoutManager = LinearLayoutManager(this)
        recyclerbranch.setHasFixedSize(true)
        // recyclerAnimals.adapter = mAdapter
        /**getData firebase*/
        getProductsData()

    }
    /**ok now create new activity*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.products ->  startActivity(Intent(this, MainProducts::class.java))

            R.id.cart ->  startActivity(Intent(this, CartActivity::class.java))
        }
        return super.onOptionsItemSelected(item)

    }
    private fun getProductsData() {

        mmDataBase = FirebaseDatabase.getInstance().getReference("Sucursales")
        mmDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (animalSnapshot in snapshot.children){
                        val animal = animalSnapshot.getValue(BranchData::class.java)
                        BranchList.add(animal!!)
                    }
                    recyclerbranch.adapter = mmAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Mainbranch,
                    error.message, Toast.LENGTH_SHORT).show()
            }

        })


    }




}