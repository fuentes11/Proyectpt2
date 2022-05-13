package com.example.ferreteria_mi_casa.products

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.example.ferreteria_mi_casa.NewActivity
import com.example.ferreteria_mi_casa.R
import com.example.ferreteria_mi_casa.databinding.ItemListBinding
import com.example.ferreteria_mi_casa.products.model.ProductData

class CartAdapter(
    var c: Context,var productList:ArrayList<ProductData>
):RecyclerView.Adapter<CartAdapter.CartViewHolder>()
{
    inner class CartViewHolder(var v: ItemListBinding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBinding>(
            inflater, R.layout.item_list,parent,
            false)
        return CartViewHolder(v)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val newList = productList[position]
        holder.v.areproducts = productList[position]
        holder.v.root.setOnClickListener {
            val image= newList.image
            val name = newList.name
            val price = newList.price

            val mIntent = Intent(c, NewActivity::class.java)
            mIntent.putExtra("key", newList.key)
            mIntent.putExtra("image",image)
            mIntent.putExtra("name",name)
            mIntent.putExtra("price",price)
            c.startActivity(mIntent)

            /**set Data*/

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}