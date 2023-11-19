package com.example.zoomato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zoomato.Fragment.Congratulations_BottomFragment
import com.example.zoomato.databinding.ActivityPayoutBinding

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
             finish();
        }
        binding.orderplacebtn.setOnClickListener {
            val congrats=Congratulations_BottomFragment()

             congrats.show(supportFragmentManager,"test")


        }

    }
}