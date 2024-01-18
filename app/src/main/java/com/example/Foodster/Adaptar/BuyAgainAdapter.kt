package com.example.Foodster.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Foodster.DetailsActivity
import com.example.Foodster.Model.MenuModel
import com.example.Foodster.databinding.BuyAgainItemBinding

class BuyAgainAdapter(
     private val menuitems:MutableList<MenuModel>,
    private val requrecontext: Context
) : RecyclerView.Adapter<BuyAgainAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: BuyAgainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.buyFoodname.text =  menuitems[position].foodname
            binding.buyprice.text = "₹ " +menuitems[position].foodprice
           Glide.with(requrecontext).load(menuitems[position].foodimage).into(binding.buyimg)


            itemView.setOnClickListener {
                val intent = Intent(requrecontext, DetailsActivity::class.java).apply{
                putExtra("MenuItemName", menuitems.get(position).foodname)
                putExtra("MenuItemImage", menuitems.get(position).foodimage)
                putExtra("MenuItemDescription", menuitems.get(position).fooddescription)
                putExtra("MenuItemPrice", menuitems.get(position).foodprice)
                putExtra("MenuItemIngrident", menuitems.get(position).foodingredients)
                putExtra("menuidd", menuitems.get(position).menuid)
                }

                requrecontext.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuitems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}