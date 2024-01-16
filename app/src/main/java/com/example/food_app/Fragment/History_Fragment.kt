package com.example.food_app.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_app.Adaptar.BuyAgainAdapter
import com.example.food_app.Model.CartItems
import com.example.food_app.Model.MenuModel
import com.example.food_app.R
import com.example.food_app.databinding.FragmentHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class History_Fragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var BuyAgainAdapter: BuyAgainAdapter
     private lateinit var cartItems: MutableList<CartItems>
     private lateinit var database:FirebaseDatabase
     private lateinit var auth:FirebaseAuth
      private lateinit var menuitmes:MutableList<MenuModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          reterive()


    }

    private fun reterive() {
       // database and auth
        database = FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        val foodref: DatabaseReference =database.reference.child("history").child(auth.currentUser!!.uid)
        cartItems= mutableListOf()
        foodref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foosnapshot in snapshot.children){
                    val fooditem=foosnapshot.getValue(CartItems::class.java)
                    cartItems.add(fooditem!!)


                }
                menuitmes= mutableListOf()
                for(i in cartItems) {
                    val fodid = i.foodid
                    database.getReference("menu").child(fodid!!).get().addOnSuccessListener {
                        if (it.exists()) {
                            val model=it.getValue(MenuModel::class.java)
                            menuitmes.add(model!!)
                        }
                    }
                }
                Log.d("mebu",menuitmes.toString())


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })


    }





    fun setuprecyclerView(m: MutableList<MenuModel>) {

        Log.d("men",menuitmes.toString())
        BuyAgainAdapter = BuyAgainAdapter(menuitmes,requireContext())
        binding.rcbuyagain.layoutManager = LinearLayoutManager(requireContext())
        binding.rcbuyagain.adapter = BuyAgainAdapter
    }


}