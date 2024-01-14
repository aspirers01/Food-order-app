package com.example.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.food_app.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailfoodname.text=intent.getStringExtra("MenuItemName");
        Glide.with(this).load(intent.getStringExtra("MenuItemImage")).into(binding.detailimg)
        binding.descriptiontextview.text=intent.getStringExtra("MenuItemDescription");
        binding.ingredenttextview.text=intent.getStringExtra("MenuItemIngrident");
        binding.imgbackBtn.setOnClickListener {
            finish()
        }

    }
}