package com.example.food_app.Adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app.Model.CartItems
import com.example.food_app.databinding.CartItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
            val retriveditem = position
            getunikeyatpostion(retriveditem) { uniqekey ->
                if (uniqekey != null) {
                    cartref.child(uniqekey).removeValue().addOnSuccessListener {
                        cartItems.removeAt(position)
                        cartItemPrices.removeAt(position)
                        cartImages.removeAt(position)
                        cartqantity.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, cartItems.size)
                        Toast.makeText(cntx, "item deleted", Toast.LENGTH_SHORT).show()

                        itemquantities =
                            itemquantities.filterIndexed { index, _ -> index != position }.toIntArray()
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, cartItems.size)
                    }.addOnFailureListener {
                        Toast.makeText(cntx, "failed to delete", Toast.LENGTH_SHORT).show()
                    }


                }

            }
        }


    private fun getunikeyatpostion(retriveditem: Int, oncomplete: (String?) -> Unit) {
        cartref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var uniqekey: String? = null
                snapshot.children.forEachIndexed() { index, child ->
                    if (index == retriveditem) {
                        uniqekey = snapshot.key
                        return@forEachIndexed
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(cntx, error.message, Toast.LENGTH_SHORT).show()
            }
        })

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


