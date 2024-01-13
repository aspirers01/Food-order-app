package com.example.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.food_app.Fragment.Notifcation_BottomFragment
import com.example.food_app.databinding.ActivityHomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home_Page : AppCompatActivity() {
    private lateinit var binding:ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomePageBinding.inflate(layoutInflater)
           setContentView(binding.root)
        //
        //settup for bottom navigation
        var NavController=findNavController(R.id.fragmentContainerView)
        var bottomnav=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomnav.setupWithNavController(NavController)

        //setup to notification button
          binding.notification.setOnClickListener{
              val bottomfragment= Notifcation_BottomFragment()
                bottomfragment.show(supportFragmentManager,"test string")
          }


    }
}