package com.example.zoomato.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomato.databinding.ItemListBinding

class PopularAdapter(
    private val Item: List<String>,
    private val Img: List<Int>,
    private val Price: List<String>
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
        return Item.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = Item[position]
        val img = Img[position]
        val price = Price[position]
        holder.binding.foodnamepopular.text = item
        holder.binding.pricePopular.text = price
        holder.binding.imageView6.setImageResource(img)


    }


}