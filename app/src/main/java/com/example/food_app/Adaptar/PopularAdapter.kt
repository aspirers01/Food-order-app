package com.example.food_app.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app.DetailsActivity
import com.example.food_app.Model.MenuModel
import com.example.food_app.databinding.ItemListBinding

class PopularAdapter(
    private val menuitems: MutableList<MenuModel>,
    private  val requrecontext: Context
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
        val price = menuitems[position].foodprice
        val uri = menuitems[position].foodimage
        holder.binding.foodnamepopular.text = item
        holder.binding.pricePopular.text = price
         Glide.with(requrecontext).load(uri).into(holder.binding.imageView6)
        holder.itemView.setOnClickListener {
            val intent = Intent(requrecontext, DetailsActivity::class.java)
            intent.putExtra("MenuItemName", menuitems.get(position).foodname)
            intent.putExtra("MenuItemImage", menuitems.get(position).foodimage)
            requrecontext.startActivity(intent)
        }


    }


}