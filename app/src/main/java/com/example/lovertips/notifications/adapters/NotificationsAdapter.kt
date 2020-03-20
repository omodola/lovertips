package com.example.lovertips.notifications.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.notifications.model.NotificationsViewModel

class NotificationsAdapter(val context: Context, private val notificationList : ArrayList<NotificationsViewModel>)
    : RecyclerView.Adapter<NotificationsAdapter.ItemViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.notifications_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.notificationtitle.setText(notificationList[position].get_title)
        holder.notificationcontent.setText(notificationList[position].get_content)
        holder.notificationImage.setImageResource(notificationList[position].get_image)
        holder.notificationtime.setText(notificationList[position].get_time)

    }


    override fun getItemCount(): Int {
        return notificationList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var notificationtitle: TextView = itemView.findViewById(R.id.notifications_title) as TextView
        var notificationImage: ImageView = itemView.findViewById(R.id.notifications_image) as ImageView
        var notificationcontent: TextView = itemView.findViewById(R.id.notifications_content) as TextView
        var notificationtime: TextView = itemView.findViewById(R.id.notifications_time) as TextView


    }


}
