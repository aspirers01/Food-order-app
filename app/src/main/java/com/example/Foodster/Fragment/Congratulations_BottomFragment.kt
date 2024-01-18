package com.example.Foodster.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.Foodster.Home_Page
import com.example.Foodster.databinding.FragmentCongratulationsBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Congratulations_BottomFragment : BottomSheetDialogFragment() {

private lateinit var binding:FragmentCongratulationsBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding= FragmentCongratulationsBottomBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goHome.setOnClickListener {
              val intent=Intent(requireContext(),Home_Page::class.java)
            dismiss()
              startActivity(intent)




        }
    }


}