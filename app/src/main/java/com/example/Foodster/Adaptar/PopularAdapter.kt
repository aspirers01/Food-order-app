package com.example.Foodster.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Foodster.DetailsActivity
import com.example.Foodster.Model.MenuModel
import com.example.Foodster.databinding.ItemListBinding

class PopularAdapter(
    private val menuitems: MutableList<MenuModel>,
    private val requrecontext: Context
) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    inner class PopularViewHolder(var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return menuitems.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = menuitems[position].foodname
        val price = "$ " + menuitems[position].foodprice
        val uri = menuitems[position].foodimage
        holder.binding.foodnamepopular.text = item
        holder.binding.pricePopular.text = price
        Glide.with(requrecontext).load(uri).into(holder.binding.imageView6)

        holder.itemView.setOnClickListener {
            val intent = Intent(requrecontext, DetailsActivity::class.java).apply {
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