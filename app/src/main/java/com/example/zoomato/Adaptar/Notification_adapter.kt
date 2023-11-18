package com.example.zoomato.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoomato.databinding.NotificationItemlistBinding

class Notification_adapter(
    private var notification: ArrayList<String>,
    private var notificationimg: ArrayList<Int>
) : RecyclerView.Adapter<Notification_adapter.ViewHolder>() {
    inner class ViewHolder(val binding: NotificationItemlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                notificationImgview.setImageResource(notificationimg[position])
                notificationTextView.text = notification[position]
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NotificationItemlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notification.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}