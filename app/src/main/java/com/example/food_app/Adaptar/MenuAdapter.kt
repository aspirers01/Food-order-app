package com.example.food_app.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app.DetailsActivity
import com.example.food_app.Model.MenuModel
import com.example.food_app.databinding.MenuItemsBinding

class MenuAdapter(
    private val menuitems: MutableList<MenuModel> = mutableListOf(),
    private val requrecontext: Context
) : RecyclerView.Adapter<MenuAdapter.MHViewHolder>() {
    inner class MHViewHolder(var binding: MenuItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                Glide.with(requrecontext).load(menuitems[position].foodimage).into(menuImg)
                menuPrice.text = "$ " + menuitems[position].foodprice
                menufoodname.text = menuitems[position].foodname

                // set onclick listenr  to open  details
                itemView.setOnClickListener {
                    val intent = Intent(requrecontext, DetailsActivity::class.java).apply {
                        putExtra("MenuItemName", menuitems.get(position).foodname)
                        putExtra("MenuItemImage", menuitems.get(position).foodimage)
                        putExtra("MenuItemDescription", menuitems.get(position).fooddescription)
                        putExtra("MenuItemPrice", menuitems.get(position).foodprice)
                        putExtra("MenuItemIngrident", menuitems.get(position).foodingredients)


                    }
                    requrecontext.startActivity(intent)

                }

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MHViewHolder {
        val binding = MenuItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MHViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuitems.size
    }

    override fun onBindViewHolder(holder: MHViewHolder, position: Int) {

        holder.bind(position)

    }

    fun setData(it: List<MenuModel>?): List<MenuModel>? {

        menuitems.clear()
        if (it != null) {
            menuitems.addAll(it)
        }
        notifyDataSetChanged()
        return menuitems
    }

}