package com.example.zoomato.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoomato.Adaptar.MenuAdapter
import com.example.zoomato.Model.MenuModel
import com.example.zoomato.R
import com.example.zoomato.databinding.FragmentSearchBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Search_Fragment : Fragment() {
    private lateinit var adapter: MenuAdapter
    private lateinit var binding: FragmentSearchBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        retrivepopularitem()

        return binding.root

    }
//  private val alterdata=mutableListOf<MenuModel>()
    //mutable live data
    private val alterdata=MutableLiveData<List<MenuModel>>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MenuAdapter(
            requrecontext=  requireContext()
        )
        binding.recycleSearch.layoutManager = LinearLayoutManager(requireContext());
        binding.recycleSearch.adapter = adapter
       Log.d("this", "onViewCreated: ${menuItems.size}")
//        setupfor recycle view search
        setupSearchView()
        alterdata.observe(viewLifecycleOwner,{
            adapter.setData(it)
        })
//        show all menu
//        showAllMenu()

    }

//

    // setup for search view;
    private fun setupSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterMenuItems(query!!)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMenuItems(newText!!)
                return true
            }
        })


    }

    private fun filterMenuItems(query: String) {

        val filteredList = mutableListOf<MenuModel>()
        for (item in menuItems) {
            if (item.foodname!!.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item)
            }
        }
        alterdata.value = filteredList
    }

    private fun retrivepopularitem() {
        // data base intilization
        database = FirebaseDatabase.getInstance()
        val foodref: DatabaseReference =database.reference.child("menu")
        Log.d("searchFragment", "onViewCreated: ${foodref.ref}")

        menuItems= mutableListOf()
        foodref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foosnapshot in snapshot.children){
                    val fooditem=foosnapshot.getValue(MenuModel::class.java)
                    menuItems.add(fooditem!!)

                }
                 alterdata.value=menuItems
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        })


    }


}
