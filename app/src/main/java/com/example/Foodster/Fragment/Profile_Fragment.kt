package com.example.Foodster.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.Foodster.Model.Profile
import com.example.Foodster.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Profile_Fragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(layoutInflater,container,false)
        //initialize fire base auth & database
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get current user id
        val currentuserid=auth.currentUser?.uid
        // if data is already set then get data from database and set it to edit text
        database.getReference("Profile").child(currentuserid!!).get().addOnSuccessListener {
            if (it.exists()){
                val profile=it.getValue(Profile::class.java)
                binding.edname.setText(profile?.username)
                binding.edaddress.setText(profile?.useraddress)
                binding.edphoneno.setText(profile?.userphone)
                binding.edemail.setText(profile?.useremail)
            }
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Error unable to fetcech data from server", Toast.LENGTH_SHORT).show()
        }

        // if data not exist then set data to database
         binding.edemail.setText(auth.currentUser?.email)
        binding.edname.setText(auth.currentUser?.displayName)
        binding.btnsaveinfo.setOnClickListener{
            val username=binding.edname.text.toString()
            val useraddress=binding.edaddress.text.toString()
            val userphone=binding.edphoneno.text.toString()
            val useremail=binding.edemail.text.toString()
            val profile=Profile(username,useraddress,useremail,userphone)
            database.getReference("Profile").child(currentuserid).setValue(profile).addOnSuccessListener {


                Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Error unable to update profile", Toast.LENGTH_SHORT).show()
            }
        }



    }


}