package com.example.zoomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zoomato.databinding.ActivityRegisteruiBinding

class Registerui : AppCompatActivity() {
    private lateinit var binding: ActivityRegisteruiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          binding=ActivityRegisteruiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.alrdyhaveacc.setOnClickListener{

            val intent=Intent(this@Registerui,loginui::class.java)
            startActivity(intent)
            finish()
        }
        binding.createacc.setOnClickListener{
            val intent=Intent(this@Registerui,Location_Ui::class.java)
            startActivity(intent)
        }

    }
}