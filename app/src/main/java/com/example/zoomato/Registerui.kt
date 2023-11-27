package com.example.zoomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zoomato.Model.UserModel
import com.example.zoomato.databinding.ActivityRegisteruiBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class Registerui : AppCompatActivity() {
    private lateinit var binding: ActivityRegisteruiBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisteruiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting up firebase
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference





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


    }

    private fun createaccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "user created succesfully", Toast.LENGTH_SHORT).show()
                    // send back to login activity to login with intent
                    val intent = Intent(this@Registerui, loginui::class.java) 
                    updateuser(username,email,password);
                    startActivity(intent)
                    finish()


                }else{
                    Toast.makeText(this, "user not created registration failed", Toast.LENGTH_SHORT).show()
                }
            }

    }
   //save user data in realtime data base
    private fun updateuser(username: String, email: String, password: String) {
        val user = UserModel(username, email, password)
        val userid= auth.currentUser?.uid!!
        database.child("users").child(userid).setValue(user)

    }

}