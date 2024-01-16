package com.example.food_app.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_app.Adaptar.CartAdapter
import com.example.food_app.Model.CartItems
import com.example.food_app.Model.MenuModel
import com.example.food_app.PayoutActivity
import com.example.food_app.R
import com.example.food_app.databinding.FragmentCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Cart_Fragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartitems:MutableList<CartItems>
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var foodname: MutableList<String>
    private lateinit var foodprice: MutableList<String>
    private lateinit var foodqantity: MutableList<Int>
    private lateinit var foodimage: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
         // get cart itmes from database
          retivredata()

        return binding.root
    }


     // getting data from firebase
    private fun retivredata() {

        database = FirebaseDatabase.getInstance()
         auth= FirebaseAuth.getInstance()
          foodname = mutableListOf()
            foodprice = mutableListOf()
            foodqantity = mutableListOf()
            foodimage = mutableListOf()


        val foodref: DatabaseReference =database.reference.child("cart").child(auth.currentUser!!.uid)
        cartitems= mutableListOf()
        foodref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foosnapshot in snapshot.children){
                    val cartItem=foosnapshot.getValue(CartItems::class.java)
                  cartItem?.foodname.let { foodname.add(it!!) }
                    cartItem?.foodprice.let { foodprice.add(it!!) }
                 cartItem?.foodimage.let { foodimage.add(it!!) }
                 cartItem?.foodcount.let { foodqantity.add(it!!)


                }}

                displaydata(foodname, foodprice, foodimage, foodqantity)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })


    }


    private fun displaydata(fooname: MutableList<String>, fooprice: MutableList<String>, fooimage: MutableList<String>, fooqantity: MutableList<Int>) {
        val adapter = CartAdapter(
            fooname,
            fooprice,
            fooimage,
            fooqantity,
            requireContext()
        )

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.cartRecyclerView.adapter = adapter
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.proceedbtn.setOnClickListener {
         val intent=Intent(requireContext(),PayoutActivity::class.java)
            startActivity(intent)

        }

    }


}


