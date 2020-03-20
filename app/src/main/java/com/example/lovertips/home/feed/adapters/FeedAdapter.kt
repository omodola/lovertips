package com.example.lovertips.home.feed.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.home.feed.ui.model.ItemModel

class FeedAdapter(val context: Context, private val imageModelArrayList : ArrayList<ItemModel>)
                                        : RecyclerView.Adapter<FeedAdapter.ItemViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.sectioned_linear_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.tag.setText(imageModelArrayList[position].gettag)
        holder.time.setText(imageModelArrayList[position].gettitle)
        holder.iv.setImageResource(imageModelArrayList[position].profile_image)
        holder.content.setText(imageModelArrayList[position].getcontent)
        holder.content_image.setImageResource(imageModelArrayList[position].post_image)

    }


    override fun getItemCount(): Int {
        return imageModelArrayList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var time: TextView
        var iv: ImageView
        var tag: TextView
        var content : TextView
        var content_image: ImageView

        init {

            time = itemView.findViewById(R.id.recycler_card_title) as TextView
            tag = itemView.findViewById(R.id.recycler_card_tag) as TextView
            iv = itemView.findViewById(R.id.user_image) as ImageView
            content = itemView.findViewById(R.id.recycler_card_content) as TextView
            content_image = itemView.findViewById(R.id.content_image) as ImageView

        }
    }


}

