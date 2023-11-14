package com.example.zoomato.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomato.databinding.MenuItemsBinding

class MenuAdapter(
    private val menuitems: MutableList<String>,
    private val menuprices: MutableList<String>,
    private val menuimgs: MutableList<Int>
) : RecyclerView.Adapter<MenuAdapter.MHViewHolder>() {
    inner class MHViewHolder(var binding: MenuItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                menuImg.setImageResource(menuimgs[position])
                menuPrice.text = menuprices[position]
                menufoodname.text = menuitems[position]
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