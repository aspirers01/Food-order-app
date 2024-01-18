package com.example.Foodster

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.Foodster.databinding.ActivityLocationUiBinding
import org.w3c.dom.Text

class Location_Ui : AppCompatActivity() {

    private lateinit var binding: ActivityLocationUiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationUiBinding.inflate(layoutInflater)

        val location_list = arrayOf("jaipur", "odisa", "gorakpur", "gonda", "sdfa", "mmmut")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, location_list)


        val autoCompleteTextView = binding.listLocation
        autoCompleteTextView.setAdapter(adapter)


        binding.addbtm.setOnClickListener {
            val intent = Intent(this@Location_Ui, addFooditem::class.java)
            startActivity(intent)
        }

        binding.btn.setOnClickListener {
            val intent = Intent(this@Location_Ui, Home_Page::class.java)
            startActivity(intent)

        }
        isButtonEnabled(false)
        binding.listLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                   isButtonEnabled(false)
                } else {

                    isButtonEnabled(true)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        setContentView(binding.root)

    }

    fun isButtonEnabled(isEnable:Boolean){
        binding.btn.isEnabled=isEnable
        if(isEnable){
            binding.btn.background.colorFilter=PorterDuffColorFilter(
                ContextCompat.getColor(this,R.color.mainred),
                PorterDuff.Mode.MULTIPLY
            )
        }else{
            binding.btn.background.colorFilter=PorterDuffColorFilter(
                ContextCompat.getColor(this,R.color.grey),
                PorterDuff.Mode.MULTIPLY
            )
        }
    }
}



