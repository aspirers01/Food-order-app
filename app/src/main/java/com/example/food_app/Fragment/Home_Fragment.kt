package com.example.food_app.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener

import com.denzcoskun.imageslider.models.SlideModel
import com.example.food_app.Adaptar.PopularAdapter
import com.example.food_app.R
import com.example.food_app.databinding.FragmentHomeBinding

class Home_Fragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
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


        val food_name = listOf("Burger", "Sandwich", "chaat", "tiikki", "momo");
        val price = listOf("15", "34", "34", "45", "20")
        val imgof_food =
            listOf(R.drawable.d1, R.drawable.d1, R.drawable.d2, R.drawable.d1, R.drawable.d2)

        val adapter = PopularAdapter(food_name, imgof_food, price,requireContext())
        binding.rcHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rcHome.adapter = adapter


        binding.viewmenubtn.setOnClickListener{
          val bottomsheet=Menu_BottomSheet_Fragment()
            bottomsheet.show(parentFragmentManager,"test")
        }

    }
}