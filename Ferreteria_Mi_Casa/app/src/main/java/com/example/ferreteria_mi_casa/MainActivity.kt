package com.example.ferreteria_mi_casa

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.ferreteria_mi_casa.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private  lateinit var  binding: ActivityMainBinding
    private lateinit var googleSingInClient: GoogleSignInClient
    private  lateinit var progressDialog: ProgressDialog
    private  lateinit var firebaseAuth: FirebaseAuth


    private  var email = ""
    private  var password =""


    private companion object{
        private const val RC_SINGIN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSingInClient = GoogleSignIn.getClient(this, googleSignInOptions)
//settings progress
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Login In...")
        progressDialog.setCanceledOnTouchOutside(false)

        //firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        //login from google
        binding.google.setOnClickListener {
            Log.d(TAG, "onCreate: ")
            val intent = googleSingInClient.signInIntent
            startActivityForResult(intent, RC_SINGIN )
        }
        //login user
        binding.btnlogin.setOnClickListener{
            startActivity(Intent(this, MainProducts::class.java))
           validate()
        }
        //sing in a new acount in class sing in
        binding.newacount.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
            binding.btnChange.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Forgot password")
                val view = layoutInflater.inflate(R.layout.email_forgotpaswword, null)
                val username = view.findViewById<EditText>(R.id.et_username)
                builder.setView(view)
                builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                    forgotPassword(username)
                })
                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()
            }
        }



    private fun forgotPassword(username : EditText){
        if (username.text.toString().isEmpty()) {
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            return
        }

        firebaseAuth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Email sent.",Toast.LENGTH_SHORT).show()
                }
            }
        }


    private fun validate() {
        //get
        email = binding.etxgmail.text.toString().trim()
        password = binding.etxpassword.text.toString().trim()
     //validate
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //format invalid
            Toast.makeText(this, "Email Invalid", Toast.LENGTH_LONG).show()
        }else if (TextUtils.isEmpty(password)){
            //Password is blank
            Toast.makeText(this, "Password is empy", Toast.LENGTH_LONG).show()
        }else{
            firebaseLogin()
        }
    }


    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Login as $email", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainProducts::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!= null){
            startActivity(Intent(this, MainProducts::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == RC_SINGIN){
        Log.d(TAG, "onActivityResult: Google Sign In")
        val accountT = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
        val account = accountT.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        }
        catch (e: Exception){
            Log.d(TAG, "onActivityResult: ${e.message}")
        }
    }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogle: firebase auth with Google")
        val credentals = GoogleAuthProvider.getCredential(account!!.idToken, null)
    firebaseAuth.signInWithCredential(credentals)
        .addOnSuccessListener { Result ->
            Log.d(TAG, "firebaseAuthWithGoogle: Succefull")

            val firebaseUser = firebaseAuth.currentUser
            val uid = firebaseUser!!.uid
            val email = firebaseUser!!.email

            Log.d(TAG, "firebaseAuthWithGoogle: Uid: $uid")
            Log.d(TAG, "firebaseAuthWithGoogle: email: $email")

            if (Result.additionalUserInfo!!.isNewUser){
                Log.d(TAG, "firebaseAuthWithGoogle: Account created.... \n$email")
                Toast.makeText(this, "Account created.... \n" +
                        "$email", Toast.LENGTH_SHORT).show()

            }else{
                Log.d(TAG, "firebaseAuthWithGoogle: Existing User.... \n$email")
                Toast.makeText(this, "Succesfull \n" +
                        "$email", Toast.LENGTH_SHORT).show()
            }
startActivity(Intent(this@MainActivity, Home::class.java))
            finish()
        }
        .addOnFailureListener { e ->
            Log.d(TAG, "firebaseAuthWithGoogle: Login Failed")
            Toast.makeText(this, "Login Failed \n" +
                    "$email", Toast.LENGTH_SHORT).show()
        }
    }

}