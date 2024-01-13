package com.example.food_app.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app.DetailsActivity
import com.example.food_app.databinding.MenuItemsBinding

class MenuAdapter(
    private val menuitems: MutableList<String>,
    private val menuprices: MutableList<String>,
    private val menuimgs: MutableList<Int>,
    private  val requrecontext:Context
) : RecyclerView.Adapter<MenuAdapter.MHViewHolder>() {
    inner class MHViewHolder(var binding: MenuItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                menuImg.setImageResource(menuimgs[position])
                menuPrice.text = "$ "+ menuprices[position]
                menufoodname.text = menuitems[position]

                // set onclick listenr  to open  details
                itemView.setOnClickListener {
                    val intent = Intent(requrecontext, DetailsActivity::class.java)
                    intent.putExtra("MenuItemName", menuitems.get(position))
                    intent.putExtra("MenuItemImage", menuimgs.get(position))
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

}