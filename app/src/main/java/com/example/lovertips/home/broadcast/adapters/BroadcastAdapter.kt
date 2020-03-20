package com.example.lovertips.home.feed.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.home.feed.ui.model.BroadcastViewModel

class BroadcastAdapter(val context: Context, private val imageModelArrayList : ArrayList<BroadcastViewModel>)
                                        : RecyclerView.Adapter<BroadcastAdapter.ItemViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.broadcast_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.broadcast_content.setText(imageModelArrayList[position].get_content)
        holder.broadcast_time.setText(imageModelArrayList[position].get_time)
        holder.broadcast_image.setImageResource(imageModelArrayList[position].get_image)
        holder.broadcast_title.setText(imageModelArrayList[position].get_title)

    }


    override fun getItemCount(): Int {
        return imageModelArrayList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var broadcast_title: TextView
        var broadcast_content: TextView
        var broadcast_time: TextView
        var broadcast_image: ImageView

        init {

            broadcast_title = itemView.findViewById(R.id.broadcast_view_title) as TextView
            broadcast_content = itemView.findViewById(R.id.brodcast_view_content) as TextView
            broadcast_image = itemView.findViewById(R.id.broadcast_view_image) as ImageView
            broadcast_time = itemView.findViewById(R.id.broadcast_view_time) as TextView



        }
    }


}

