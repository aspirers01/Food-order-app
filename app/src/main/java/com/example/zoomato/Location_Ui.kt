package com.example.zoomato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.zoomato.databinding.ActivityLocationUiBinding
import com.example.zoomato.databinding.ActivityRegisteruiBinding

class Location_Ui : AppCompatActivity() {

    private lateinit var binding: ActivityLocationUiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLocationUiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val location_list= arrayOf("jaipur","odisa","gorakpur","gonda","sdfa","mmmut")
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,location_list)


        val autoCompleteTextView=binding.listLocation
        autoCompleteTextView.setAdapter(adapter)

    }
}