package com.example.ferreteria_mi_casa.branchs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.example.ferreteria_mi_casa.NewActivity
import com.example.ferreteria_mi_casa.NewBranch
import com.example.ferreteria_mi_casa.R
import com.example.ferreteria_mi_casa.branchs.model2.BranchData
import com.example.ferreteria_mi_casa.databinding.ItemList2Binding
import com.example.ferreteria_mi_casa.databinding.ItemListBinding
import com.example.ferreteria_mi_casa.products.model.ProductData

class BranchAdapter(
    var c: Context,var BranchList:ArrayList<BranchData>
):RecyclerView.Adapter<BranchAdapter.BranchViewHolder>()
{
    inner class BranchViewHolder(var v: ItemList2Binding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemList2Binding>(
            inflater, R.layout.item_list2,parent,
            false)
        return BranchViewHolder(v)

    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val newList = BranchList[position]
        holder.v.arebranch = BranchList[position]
        holder.v.root.setOnClickListener {
            val image= newList.image
            val name = newList.name
            val info = newList.info

            val mIntent = Intent(c, NewBranch::class.java)
            mIntent.putExtra("image",image)
            mIntent.putExtra("name",name)
            mIntent.putExtra("info",info)
            c.startActivity(mIntent)

            /**set Data*/

        }
    }

    override fun getItemCount(): Int {
        return BranchList.size
    }
}