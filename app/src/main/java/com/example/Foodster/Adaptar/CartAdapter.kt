package com.example.Foodster.Adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Foodster.databinding.CartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemPrices: MutableList<String>,
    private val cartImages: MutableList<String>,
    private val cartqantity: MutableList<Int>,
    private val cntx: Context
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    // auth instance
    private val auth = FirebaseAuth.getInstance()

    // database instance
    init {
        val database = FirebaseDatabase.getInstance()
        val userid = auth.currentUser!!.uid
        val cartitemno = cartItems.size
        itemquantities = IntArray(cartitemno) { 1 }
        cartref = database.reference.child("cart").child(userid)


    }

    companion object {
        var itemquantities: IntArray= intArrayOf()
        lateinit var cartref: DatabaseReference

    }

    inner class ViewHolder(var binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.cartFoodName.text = cartItems[position]
            binding.cartFoodprice.text = "$ " + cartItemPrices[position]
            Glide.with(cntx).load(cartImages[position]).into(binding.cartImage)
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
                cartref.child(cartItems[position]).child("foodcount")
                    .setValue(itemquantities[position])



            } else {
                delete(position)
            }

        }

        private fun increase(position: Int) {
            if (itemquantities[position] < 10) {
                itemquantities[position]++
                binding.quantity.text = itemquantities[position].toString()
                cartref.child(cartItems[position]).child("foodcount")
                    .setValue(itemquantities[position])
            } else {
                Toast.makeText(cntx, "item quantity is maximum ", Toast.LENGTH_SHORT).show()
            }

        }

        private fun delete(position: Int) {

            cartref.child(cartItems[position]).removeValue()
            cartItems.removeAt(position)
            cartItemPrices.removeAt(position)
            cartImages.removeAt(position)
            cartqantity.removeAt(position)
            notifyDataSetChanged()

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


