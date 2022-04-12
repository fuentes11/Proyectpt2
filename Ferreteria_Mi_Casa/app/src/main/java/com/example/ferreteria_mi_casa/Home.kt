package com.example.ferreteria_mi_casa

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.ferreteria_mi_casa.databinding.ActivityHomeBinding
import com.example.ferreteria_mi_casa.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    private  lateinit var  binding: ActivityHomeBinding
    private lateinit var  actionBar: ActionBar
    private  lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        chekuser()

        binding.btnlogout.setOnClickListener {
            firebaseAuth.signOut()
            chekuser()
        }
    }

    private fun chekuser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!= null) {
            val email = firebaseUser.email
            binding.txtemail.text = email

        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


}