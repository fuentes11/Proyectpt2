package com.example.ferreteria_mi_casa.branchs.model2

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ferreteria_mi_casa.R

class branchDb {
    fun getProgessDrawable(c: Context): CircularProgressDrawable {
        return CircularProgressDrawable(c).apply {
            strokeWidth = 5f
            centerRadius = 40f
            start()
        }

    }

    /**set Images*/
    fun ImageView.loadImage(uri:String?, progressDawable: CircularProgressDrawable){

        val option = com.bumptech.glide.request.RequestOptions().placeholder(progressDawable)
            .error(R.mipmap.ic_launcher)

        Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(uri)
            .into(this)

    }

    @BindingAdapter("android:imageUrl")
    fun loadImage(view: ImageView, url:String){
        view.loadImage(url, getProgessDrawable(view.context))
    }
}