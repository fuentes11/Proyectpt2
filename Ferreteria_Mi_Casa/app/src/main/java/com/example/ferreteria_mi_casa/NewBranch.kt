package com.example.ferreteria_mi_casa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.activity_new_branch.*

class NewBranch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_branch)

        /**get Data*/
        val BranchIntent = intent
        val BranchName = BranchIntent.getStringExtra("name")
        val Branchinfo = BranchIntent.getStringExtra("info")
        val BranchImage = BranchIntent.getStringExtra("image")

        /**call text and images*/
        name2.text = BranchName
        info2.text = Branchinfo
        image2.loadImage(BranchImage, getProgessDrawable(this))
    }
    /**ok now run it */
}