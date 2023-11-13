package com.example.zoomato.Adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomato.databinding.CartItemBinding

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<String>,
    private val cartImages: MutableList<Int>,
    private val cntx: Context
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private val itemquantities = IntArray(cartItems.size) { 1 }

    inner class ViewHolder(var binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.cartFoodName.text = cartItems[position]
            binding.cartFoodprice.text = "$ "+cartItemPrices[position]
            binding.cartImage.setImageResource(cartImages[position])
            binding.quantity.text = itemquantities[position].toString()
            binding.increaseQuantity.setOnClickListener {
                increase(position)
            }
            binding.decreaseQuantity.setOnClickListener {
                decrease(position)
            }
            binding.deleteQuantity.setOnClickListener {
                val itemposition = adapterPosition
                if (itemposition != RecyclerView.NO_POSITION) {
                    delete(position)
                }
            }

        }

        private fun decrease(position: Int) {
            if (itemquantities[position] > 1) {
                itemquantities[position]--
                binding.quantity.text = itemquantities[position].toString()
            } else {
                delete(position)
            }

        }

        private fun increase(position: Int) {
            if (itemquantities[position] < 10) {
                itemquantities[position]++
                binding.quantity.text = itemquantities[position].toString()
            } else {
                Toast.makeText(cntx, "item quantity is maximum ", Toast.LENGTH_SHORT).show()
            }

        }

        private fun delete(position: Int) {
            cartImages.removeAt(position)
            cartItems.removeAt(position)
            cartItemPrices.removeAt(position)
            notifyItemRemoved(position)
            Toast.makeText(cntx, "item deleted", Toast.LENGTH_SHORT).show()
            notifyItemRangeChanged(position, cartItems.size)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }


}