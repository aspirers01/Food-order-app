package com.example.food_app.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_app.Adaptar.BuyAgainAdapter
import com.example.food_app.R
import com.example.food_app.databinding.FragmentHistoryBinding

class History_Fragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var BuyAgainAdapter: BuyAgainAdapter

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


        setuprecyclerView()

    }

    fun setuprecyclerView() {
        val byfoodname = arrayListOf<String>("bruger", "momo", "pizza")
        val byfoodprice = arrayListOf<String>("45", "50", "100")
        val buyimg = arrayListOf<Int>(R.drawable.d1, R.drawable.d2, R.drawable.d1)
        BuyAgainAdapter = BuyAgainAdapter(byfoodname, byfoodprice, buyimg,requireContext())
        binding.rcbuyagain.layoutManager = LinearLayoutManager(requireContext())
        binding.rcbuyagain.adapter = BuyAgainAdapter
    }


}