package com.example.zoomato.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomato.databinding.BuyAgainItemBinding
import com.example.zoomato.databinding.FragmentSearchBinding

class BuyAgainAdapter(
    private val buyitems: ArrayList<String>,
    private val buyprices: ArrayList<String>,
    private val buyimgs: ArrayList<Int>
) : RecyclerView.Adapter<BuyAgainAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: BuyAgainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.buyFoodname.text=buyitems[position]
            binding.buyprice.text=buyprices[position]
            binding.buyimg.setImageResource(buyimgs[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return buyitems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}