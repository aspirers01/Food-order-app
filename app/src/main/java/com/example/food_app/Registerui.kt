package com.example.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.food_app.Model.UserModel
import com.example.food_app.databinding.ActivityRegisteruiBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class Registerui : AppCompatActivity() {
    private lateinit var binding: ActivityRegisteruiBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisteruiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting up firebase
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference


           //setup for already have account button

        binding.alrdyhaveacc.setOnClickListener {

            val intent = Intent(this@Registerui, loginui::class.java)
            startActivity(intent)
            finish()
        }
        binding.createacc.setOnClickListener {
            // setup valuse of views to variables
            username = binding.username.text.toString().trim()
            email = binding.usermail.text.toString().trim()
            password = binding.userpassword.text.toString().trim()
            //check if there no empty fields
            if (username.isBlank() || email.isBlank() || password.isBlank()) {
                if (username.isBlank())
                    binding.username.error = "Please enter your name"
                if (email.isBlank())
                    binding.usermail.error = "Please enter your email"
                if (password.isBlank())
                    binding.userpassword.error = "Please enter your password"
                Toast.makeText(this, "please fill all details", Toast.LENGTH_SHORT).show()

            } else {

//                   if everthing is right register the user with given data email and password
//                 save user data in realtime data base
                createaccount(email, password)

            }
        }
         val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        //setup for google sign in button
        binding.googlebtn.setOnClickListener {

            val signInIntentRequest = googleSignInClient.signInIntent
            launcher.launch(signInIntentRequest)

        }


    }
 private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            try {
                val account:GoogleSignInAccount? =task.result
              val credintial= GoogleAuthProvider.getCredential(account?.idToken,null)
                auth.signInWithCredential(credintial).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        updateuser(account?.displayName.toString(),account?.email.toString(),"")
                        Toast.makeText(this, "Google sign in success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Registerui, loginui::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
                    }
                }
                Toast.makeText(this, "Google sign in success", Toast.LENGTH_SHORT).show()
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
        }
    }



    private fun createaccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "user created succesfully", Toast.LENGTH_SHORT).show()
                    // send back to login activity to login with intent
                    val intent = Intent(this@Registerui, loginui::class.java)
                    updateuser(username, email, password);
                    startActivity(intent)
                    finish()


                } else {
                    Toast.makeText(this, "user not created registration failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }

    //save user data in realtime data base
    private fun updateuser(username: String, email: String, password: String) {
        val user = UserModel(username, email, password)
        val userid = auth.currentUser?.uid!!
        database.child("users").child(userid).setValue(user)

    }

}