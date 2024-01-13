package com.example.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.food_app.databinding.ActivityLoginuiBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class loginui : AppCompatActivity() {
    private lateinit var binding: ActivityLoginuiBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginuiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting up firebase
        auth = Firebase.auth
        database = Firebase.database.reference

        //login button
        binding.loginbtn.setOnClickListener {
            email = binding.useremail.text.toString().trim()
            password = binding.userpasswrod.text.toString().trim()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "please fill all details", Toast.LENGTH_SHORT).show()
                if (email.isBlank())
                    binding.useremail.error = "Please enter your email"
                if (password.isBlank())
                    binding.userpasswrod.error = "Please enter your password"
            } else {
                loginuser(email, password)
            }
        }

        binding.txtdonhaveacc.setOnClickListener {
            val intent = Intent(this@loginui, Registerui::class.java)
            startActivity(intent)
        }




        }


    private fun loginuser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "login is succesfull", Toast.LENGTH_SHORT).show()

                val user: FirebaseUser? = auth.currentUser
                updateUI(user)

            } else {
                binding.useremail.error = "Please enter valid email"
                binding.userpasswrod.error = "Please enter valid password"
            }
        }

    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this@loginui, Location_Ui::class.java)
        startActivity(intent)
        finish()
    }



    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = auth.currentUser
        if (user != null) {
            updateUI(user)
        }
    }


}