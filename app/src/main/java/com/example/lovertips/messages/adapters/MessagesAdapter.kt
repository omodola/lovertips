package com.example.lovertips.messages.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.messages.model.MessagesViewModel

class MessagesAdapter(val context: Context, private val messagesList : ArrayList<MessagesViewModel>) : RecyclerView.Adapter<MessagesAdapter.ItemViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.messages_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.messages_title.setText(messagesList[position].get_title)
        holder.messages_content.setText(messagesList[position].get_content)
        holder.messages_image.setImageResource(messagesList[position].get_image)
    }


    override fun getItemCount(): Int {
        return messagesList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var messages_title: TextView
        var messages_image: ImageView
        var messages_content: TextView

        init {

            messages_title = itemView.findViewById(R.id.messages_title) as TextView
            messages_content = itemView.findViewById(R.id.messages_content) as TextView
            messages_image = itemView.findViewById(R.id.messages_image) as ImageView
        }
    }


}
