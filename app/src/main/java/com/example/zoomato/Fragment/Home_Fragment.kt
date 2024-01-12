package com.example.zoomato.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener

import com.denzcoskun.imageslider.models.SlideModel
import com.example.zoomato.Adaptar.PopularAdapter
import com.example.zoomato.Model.MenuModel
import com.example.zoomato.R
import com.example.zoomato.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.ValueEventListener

class Home_Fragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // retrive data from firebase
          retrivepopularitem()
        Log.d("home", "onViewCreated: ${menuItems}")
        return binding.root
    }

    private fun retrivepopularitem() {
        // data base intilization
        database = FirebaseDatabase.getInstance()
        val foodref:DatabaseReference=database.reference.child("menu")
        menuItems= mutableListOf()
         foodref.addListenerForSingleValueEvent(object : ValueEventListener {
             override fun onDataChange(snapshot: DataSnapshot) {
                  for (foosnapshot in snapshot.children){
                      val fooditem=foosnapshot.getValue(MenuModel::class.java)
                      menuItems.add(fooditem!!)

                      displaypopularitem(menuItems)
                  }
             }

             override fun onCancelled(error: DatabaseError) {
                 Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
             }
         })


    }

    private fun displaypopularitem(menuItems: MutableList<MenuModel>) {
        val adapter = PopularAdapter(menuItems, requireContext())
        binding.rcHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rcHome.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
        imageList.add(SlideModel(R.drawable.d1))
        imageList.add(SlideModel("https://bit.ly/2YoJ77H", ScaleTypes.FIT))
        imageList.add(SlideModel("https://bit.ly/2BteuF2", ScaleTypes.FIT))
        imageList.add(SlideModel("https://bit.ly/3fLJf72", ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {

                val itemmessage = "select image $position"
                Toast.makeText(requireContext(), itemmessage, Toast.LENGTH_SHORT).show()
            }
        })


//

        binding.viewmenubtn.setOnClickListener {
            val bottomsheet = Menu_BottomSheet_Fragment()
            bottomsheet.show(parentFragmentManager, "test")
        }

    }


}