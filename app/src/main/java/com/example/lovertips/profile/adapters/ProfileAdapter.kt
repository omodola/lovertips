package com.example.lovertips.profile.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.profile.model.ProfileViewModel


class ProfileAdapter constructor(
    val context: Context, private val profileList : ArrayList<ProfileViewModel>, val clickListener: OnClickProfile)
    : RecyclerView.Adapter<ProfileAdapter.ItemViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.profile_view, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       /* holder.profile_content.setText(profileList[position].get_content)
        holder.profile_image.setImageResource(profileList[position].get_image)*/

        holder.initialize(profileList.get(position), clickListener)
    }


    override fun getItemCount(): Int {
        return profileList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var profile_content: TextView
        var profile_image: ImageView


        init {

            profile_content = itemView.findViewById(R.id.profile_content) as TextView
            profile_image = itemView.findViewById(R.id.profile_icon) as ImageView
        }

        fun initialize(item:ProfileViewModel, action:OnClickProfile){
            profile_content.text = item.get_content
            profile_image.setImageResource(item.get_image)

            itemView.setOnClickListener{
                action.onItemClicked(item, adapterPosition)
            }
        }


    }

    interface OnClickProfile{
        fun onItemClicked(item:ProfileViewModel, position: Int)
    }


}
