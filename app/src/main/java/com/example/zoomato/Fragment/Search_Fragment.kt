package com.example.zoomato.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoomato.Adaptar.MenuAdapter
import com.example.zoomato.R
import com.example.zoomato.databinding.FragmentSearchBinding


 class Search_Fragment : Fragment() {
    private lateinit var adapter: MenuAdapter
    private lateinit var binding: FragmentSearchBinding
   private val food_name = listOf("Burger", "Sandwich", "chaat", "tiikki", "momo");
   private val price = listOf("15", "34", "34", "45", "20")
   private val imgof_food =
        listOf(R.drawable.d1, R.drawable.d1, R.drawable.d2, R.drawable.d1, R.drawable.d2)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    private val filtermenufood_name = mutableListOf<String>()
    private val filterPrice = mutableListOf<String>()
    private val filterimg = mutableListOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MenuAdapter(
            filtermenufood_name, filterPrice, filterimg
        )
        binding.recycleSearch.layoutManager = LinearLayoutManager(requireContext());
        binding.recycleSearch.adapter = adapter

//        setupfor recycle view search
        setupSearchView()
//        show all menu
        showAllMenu()

    }

    private fun showAllMenu() {
        filtermenufood_name.clear()
        filterPrice.clear()
        filterimg.clear()


            filterPrice.addAll(price)
            filterimg.addAll(imgof_food)
            filtermenufood_name.addAll(food_name)
        adapter.notifyDataSetChanged()
        }



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
        filtermenufood_name.clear()
        filterPrice.clear()
        filterimg.clear()
        food_name.forEachIndexed { index, food_name ->
            if (food_name.contains(query, ignoreCase = true)) {
                filterPrice.add(price[index])
                filterimg.add(imgof_food[index])
                filtermenufood_name.add(food_name)
            }


        }
        adapter.notifyDataSetChanged()
    }

}
