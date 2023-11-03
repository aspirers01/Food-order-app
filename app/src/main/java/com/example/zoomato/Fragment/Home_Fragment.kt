package com.example.zoomato.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener

import com.denzcoskun.imageslider.models.SlideModel
import com.example.zoomato.R
import com.example.zoomato.databinding.FragmentHomeBinding

class Home_Fragment : Fragment() {
  private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
        imageList.add(SlideModel(R.drawable.d1))
        imageList.add(SlideModel("https://bit.ly/2YoJ77H",ScaleTypes.FIT))
        imageList.add(SlideModel("https://bit.ly/2BteuF2" ,ScaleTypes.FIT))
        imageList.add(SlideModel("https://bit.ly/3fLJf72",ScaleTypes.FIT), )

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
          imageSlider.setItemClickListener(object: ItemClickListener{
              override fun doubleClick(position: Int) {
                  TODO("Not yet implemented")
              }

              override fun onItemSelected(position: Int) {

                  val itemmessage="select image $position"
                  Toast.makeText(requireContext(), itemmessage, Toast.LENGTH_SHORT).show()
              }
          })
    }
}