package com.example.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.food_app.Fragment.Congratulations_BottomFragment
import com.example.food_app.Model.CartItems
import com.example.food_app.Model.MenuModel
import com.example.food_app.Model.Profile
import com.example.food_app.databinding.ActivityPayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPayoutBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databse:FirebaseDatabase
private lateinit var caritems:MutableList<CartItems>
private  var ans:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // firebase auth & database intialization here
        auth= FirebaseAuth.getInstance()
        databse= FirebaseDatabase.getInstance()
        binding.btnBack.setOnClickListener {
             finish();
        }
        binding.orderplacebtn.setOnClickListener {
            val congrats=Congratulations_BottomFragment()

             congrats.show(supportFragmentManager,"test")


        }

        databse.getReference("Profile").child(auth.currentUser?.uid!!).get().addOnSuccessListener {
            if (it.exists()){
                val profile=it.getValue(Profile::class.java)
                binding.ednamepy.setText(profile?.username)
                binding.edaddrespy.setText(profile?.useraddress)
                binding.edphnopy.setText(profile?.userphone)

            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error unable to fetcech data from server", Toast.LENGTH_SHORT).show()
        }

        // reterive data from data base for cart items
        reterive()

        Log.d("ans", "onCreate: ${ans}")

    }

    private fun reterive() {
        val foodref: DatabaseReference =databse.reference.child("cart").child(auth.currentUser?.uid!!)
        caritems= mutableListOf()

        foodref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foosnapshot in snapshot.children){
                    val fooditem=foosnapshot.getValue(CartItems::class.java)
                    caritems.add(fooditem!!)
                    Log.d("pricecheck", "onDataChange: ${fooditem.foodprice}")
                     ans+=fooditem.foodprice!!.toInt()*fooditem.foodcount!!
                    Log.d("pricecheck", "onDataChange: ${ans}")


                }
                binding.edamountpy.setText(ans.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PayoutActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}