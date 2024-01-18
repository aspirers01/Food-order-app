package com.example.Foodster.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Foodster.Adaptar.BuyAgainAdapter
import com.example.Foodster.Model.CartItems
import com.example.Foodster.Model.MenuModel
import com.example.Foodster.databinding.FragmentHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class History_Fragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var BuyAgainAdapter: BuyAgainAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var menuitmes: MutableList<MenuModel>
    private lateinit var cartitems: MutableList<CartItems>

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

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val cartref: DatabaseReference = database.reference.child("history").child(auth.currentUser?.uid!!)
        cartitems = mutableListOf()

        cartref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (cartsnapshot in snapshot.children) {
                    val cartitem = cartsnapshot.getValue(CartItems::class.java)
                    cartitems.add(cartitem!!)

//
                }
                retriveitem(cartitems)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun retriveitem(cartitems: MutableList<CartItems>) {
//          Log.d("cart",cartitems.toString())
        menuitmes = mutableListOf()
        for (i in cartitems) {
               val fdid=i.foodid
            val foodref: DatabaseReference = database.reference.child("menu").child(fdid!!)
              foodref.get().addOnSuccessListener {
                  if (it.exists()){
                      val fooditem=it.getValue(MenuModel::class.java)
                      menuitmes.add(fooditem!!)
                      setuprecyclerview(menuitmes)
                  }
              }.addOnFailureListener {
                  Toast.makeText(requireContext(), "Error unable to fetcech data from server", Toast.LENGTH_SHORT).show()
              }


        }



    }

    private fun setuprecyclerview(menuitmes: MutableList<MenuModel>) {
        BuyAgainAdapter = BuyAgainAdapter( menuitmes,requireContext())
        binding.rcbuyagain.adapter = BuyAgainAdapter
        binding.rcbuyagain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}

