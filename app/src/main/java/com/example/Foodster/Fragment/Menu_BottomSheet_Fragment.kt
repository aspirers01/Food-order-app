package com.example.Foodster.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Foodster.Adaptar.MenuAdapter
import com.example.Foodster.Model.MenuModel
import com.example.Foodster.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Menu_BottomSheet_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    private lateinit var databse:FirebaseDatabase
    private lateinit var menuitems:MutableList<MenuModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backbtn.setOnClickListener{
            dismiss()
        }
        retrivepopularitem()


    }

    private fun retrivepopularitem() {


        databse = FirebaseDatabase.getInstance()
        val foodref: DatabaseReference = databse.reference.child("menu")
        menuitems = mutableListOf()
        foodref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foosnapshot in snapshot.children) {
                    val fooditem = foosnapshot.getValue(MenuModel::class.java)
                    menuitems.add(fooditem!!)
                    displsyitem(menuitems)



                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displsyitem(menuitems: MutableList<MenuModel>) {
        val adapter = MenuAdapter(
            menuitems,
            requireContext()
        )
        binding.recyclerbottomsheet.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerbottomsheet.adapter = adapter

    }

}