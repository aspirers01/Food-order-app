package com.example.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_app.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailfoodname.text=intent.getStringExtra("MenuItemName");
        binding.detailimg.setImageResource(intent.getIntExtra("MenuItemImage",0))
        binding.imgbackBtn.setOnClickListener {
            finish()
        }

    }
}