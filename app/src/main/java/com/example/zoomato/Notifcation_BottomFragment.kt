package com.example.zoomato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoomato.Adaptar.Notification_adapter
import com.example.zoomato.databinding.FragmentNotifcationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class Notifcation_BottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNotifcationBottomBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotifcationBottomBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notification = listOf(
            "Your Order has been Cancelled  Successfully",
            "Order has been picked",
            "Congrats Your Order Placed"
        )
        val notificationimg =
            listOf(R.drawable.sademoji, R.drawable.truck, R.drawable.congratulation)
        val adapter = Notification_adapter(ArrayList(notification), ArrayList(notificationimg))

        binding.notfifacitonrc.layoutManager = LinearLayoutManager(requireContext())
        binding.notfifacitonrc.adapter = adapter
    }


}