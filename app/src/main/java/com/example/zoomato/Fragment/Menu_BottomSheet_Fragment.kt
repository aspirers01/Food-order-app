package com.example.zoomato.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoomato.Adaptar.MenuAdapter
import com.example.zoomato.R
import com.example.zoomato.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Menu_BottomSheet_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

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
        val food_name = listOf(
            "Burger",
            "Sandwich",
            "chaat",
            "tiikki",
            "momo",
            "Burger",
            "Sandwich",
            "chaat",
            "tiikki",
            "momo"
        );
        val price = listOf("15", "34", "34", "45", "20", "15", "34", "34", "45", "20")
        val imgof_food =
            listOf(
                "15", "34", "34", "45", "20", "15", "34", "34", "45", "20"

            )

//        val adapter = MenuAdapter(
//            ArrayList(food_name),
//            ArrayList(price),
//            ArrayList(imgof_food),
//            requireContext()
//        )
//        binding.recyclerbottomsheet.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerbottomsheet.adapter = adapter

    }


}