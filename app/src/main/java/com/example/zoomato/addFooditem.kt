package com.example.zoomato

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.zoomato.databinding.ActivityAddFooditemBinding

class addFooditem : AppCompatActivity() {
    private lateinit var binding: ActivityAddFooditemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityAddFooditemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val foodname=binding.foodNameEdt.text.toString()
        val foodprice=binding.foodPriceEdt.text.toString()
        val fooddesc=binding.discriptionEdt.text.toString()
        val fooding=binding.ingredentEdt.text.toString()

        binding.photoBtn.setOnClickListener{
            // it launches the option to select image from gallery
            //and also set it to imageview
            pickimage.launch("image/*")
        }


        binding.uploadBtn.setOnClickListener{

        }
    }

    //code for selecting image from gallery and setting it to imageview
    val pickimage=registerForActivityResult(ActivityResultContracts.GetContent()){
        if (it!=null)
            binding.selectedimg.setImageURI(it)
    }
}