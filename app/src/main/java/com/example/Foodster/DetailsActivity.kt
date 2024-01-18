package com.example.Foodster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.Foodster.Model.CartItems
import com.example.Foodster.databinding.ActivityDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var cartItems: CartItems
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailfoodname.text = intent.getStringExtra("MenuItemName");
        Glide.with(this).load(intent.getStringExtra("MenuItemImage")).into(binding.detailimg)
        binding.descriptiontextview.text = intent.getStringExtra("MenuItemDescription");
        binding.ingredenttextview.text = intent.getStringExtra("MenuItemIngrident");
        binding.imgbackBtn.setOnClickListener {
            finish()
        }

        binding.addtocart.setOnClickListener {
            additemtocart()
        }

    }

    private fun additemtocart() {
        val database = FirebaseDatabase.getInstance()
        val cartref = database.getReference("cart")
        val auth = FirebaseAuth.getInstance()
        val userid= auth.currentUser?.uid!!

        val cartitem = CartItems(
            intent.getStringExtra("MenuItemName"),
            intent.getStringExtra("MenuItemImage"),
            intent.getStringExtra("MenuItemPrice"),
            1,
            intent.getStringExtra("menuidd")

        )
        // add item to cart
        // if item is not in cart then add it to cart
        cartref.child(userid).child(intent.getStringExtra("MenuItemName")!!).setValue(cartitem)
            .addOnSuccessListener {
                Toast.makeText(this, "added to cart", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
            Toast.makeText(this, "failed to add", Toast.LENGTH_SHORT).show()
        }

    }
}