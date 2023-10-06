package com.example.zoomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zoomato.databinding.ActivityLoginuiBinding
import com.example.zoomato.databinding.ActivityMainBinding

class loginui : AppCompatActivity() {
    private lateinit var binding: ActivityLoginuiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=ActivityLoginuiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtdonhaveacc.setOnClickListener{
             val intent=Intent(this@loginui,Registerui::class.java)
            startActivity(intent)
        }


    }
}