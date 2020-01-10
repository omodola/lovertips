package com.example.lovertips.profile.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.profile.model.ProfileViewModel

class ProfileAdapter(val context: Context, private val messagesList : ArrayList<ProfileViewModel>) : RecyclerView.Adapter<ProfileAdapter.ItemViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.profile_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.messages_title.setText(messagesList[position].get_title)
        holder.messages_content.setText(messagesList[position].get_content)
        holder.messages_date.setText(messagesList[position].get_date)
        holder.messages_image.setImageResource(messagesList[position].get_image)
    }


    override fun getItemCount(): Int {
        return messagesList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var messages_title: TextView
        var messages_image: ImageView
        var messages_content: TextView
        var messages_date: TextView


        init {

            messages_title = itemView.findViewById(R.id.name) as TextView
            messages_content = itemView.findViewById(R.id.txtStatusMsg) as TextView
            messages_image = itemView.findViewById(R.id.profilePic) as ImageView
            messages_date = itemView.findViewById(R.id.timestamp) as TextView
        }
    }


}
