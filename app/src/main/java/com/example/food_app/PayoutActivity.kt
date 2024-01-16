package com.example.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
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
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayoutBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databse: FirebaseDatabase
    private var caritems: List<CartItems> = emptyList()
    private var history: List<CartItems> = emptyList()
    private var ans: MutableLiveData<Float> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // firebase auth & database intialization here
        auth = FirebaseAuth.getInstance()
        databse = FirebaseDatabase.getInstance()
        binding.btnBack.setOnClickListener {
            finish();
        }
        caritems = mutableListOf()
        binding.orderplacebtn.setOnClickListener {
            val congrats = Congratulations_BottomFragment()


                   history=caritems
            databse.reference.child("cart").child(auth.currentUser?.uid!!).removeValue()
            databse.reference.child("history").child(auth.currentUser?.uid!!).setValue(history)

            congrats.show(supportFragmentManager, "test")


        }

        databse.getReference("Profile").child(auth.currentUser?.uid!!).get().addOnSuccessListener {
            if (it.exists()) {
                val profile = it.getValue(Profile::class.java)
                binding.ednamepy.setText(profile?.username)
                binding.edaddrespy.setText(profile?.useraddress)
                binding.edphnopy.setText(profile?.userphone)

            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error unable to fetcech data from server", Toast.LENGTH_SHORT)
                .show()
        }

        // reterive data from data base for cart items
        reterive()
        ans.observe(this, {
            binding.edamountpy.text = "$ " + it.toString()
        })
        Log.d("cartcheck", "onCreate: ${caritems} ")


    }

    private fun reterive() {
        if(auth.currentUser==null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch{

            var cartref: DatabaseReference = databse.reference.child("cart").child(auth.currentUser?.uid!!)
            caritems= cartref.snapshots.map { snapshot ->
            snapshot.children.mapNotNull {
                it.getValue(CartItems::class.java)


            }

        }.first()
          var temp=0.0
            for (i in caritems) {
                val x = i.foodcount!!*i.foodprice!!.toFloat()
                temp+=x

            }
            ans.value=temp.toFloat()
        }

    }



}
