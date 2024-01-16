package com.example.food_app

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.food_app.Model.MenuModel
import com.example.food_app.databinding.ActivityAddFooditemBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage

class addFooditem : AppCompatActivity() {
    private lateinit var binding: ActivityAddFooditemBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var foodname: String
    private lateinit var foodprice: String
    private lateinit var fooddesc: String
    private lateinit var fooding: String
    private var foodimage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFooditemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing firebase
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference



        binding.photoBtn.setOnClickListener {
            // it launches the option to select image from gallery
            //and also set it to imageview
            pickimage.launch("image/*")
        }


        binding.uploadBtn.setOnClickListener {
            // getting the values from views
            foodname = binding.foodNameEdt.text.toString()
            foodprice = binding.foodPriceEdt.text.toString()
            fooddesc = binding.discriptionEdt.text.toString()
            fooding = binding.ingredentEdt.text.toString()

            //checking if any field is empty
            if (foodname.isBlank() || foodprice.isBlank() || fooddesc.isBlank() || fooding.isBlank() || foodimage == null) {
                if (foodname.isBlank())
                    binding.foodNameEdt.error = "Please enter food name"
                if (foodprice.isBlank())
                    binding.foodPriceEdt.error = "Please enter food price"
                if (fooddesc.isBlank())
                    binding.discriptionEdt.error = "Please enter food description"
                if (fooding.isBlank())
                    binding.ingredentEdt.error = "Please enter food ingredients"
                if (foodimage == null)
                    Toast.makeText(
                        this,
                        "please fill all data and select image ",
                        Toast.LENGTH_SHORT
                    ).show()
            } else {
                //if everything is right upload the data to firebase
                uploaddata()

            }
        }
    }

    private fun uploaddata() {
        // get a reference of menu node from realtime database
        val ref = database.child("menu")
        //get a unique key from database
        val id = ref.push().key
        //create a hashmap to store data
        if (foodimage != null) {
            val storage = FirebaseStorage.getInstance().reference.child("menu/${id}.jpg")
            storage.putFile(foodimage!!).addOnCompleteListener {
                if (it.isSuccessful) {
                    storage.downloadUrl.addOnSuccessListener {
                        val newitem =
                            MenuModel(foodname, foodprice, it.toString(), fooddesc, fooding,id)
                        ref.child(id!!).setValue(newitem)
                    }
                    Toast.makeText(this, "Food item added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "upload is failed pleasse try again ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    //code for selecting image from gallery and setting it to imageview
    val pickimage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null)
            binding.selectedimg.setImageURI(it)
        foodimage = it
    }
}