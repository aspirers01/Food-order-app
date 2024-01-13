package com.example.food_app.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_app.Adaptar.CartAdapter
import com.example.food_app.PayoutActivity
import com.example.food_app.R
import com.example.food_app.databinding.FragmentCartBinding

class Cart_Fragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        val cartitem = listOf<String>("burger", "pizza", "momo", "chicken", "noodles")
        val cartitemprize = listOf<String>("45", "68", "67", "78", "89")
        val cartimage = listOf<Int>(
            R.drawable.d2,
            R.drawable.d2,
            R.drawable.d1,
            R.drawable.d1,
            R.drawable.cesmmmut5
        )
        val adapter = CartAdapter(
            ArrayList(cartitem),
            ArrayList(cartitemprize),
            ArrayList(cartimage),
            requireActivity()
        )

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.proceedbtn.setOnClickListener {
         val intent=Intent(requireContext(),PayoutActivity::class.java)
            startActivity(intent)

        }

    }


}


