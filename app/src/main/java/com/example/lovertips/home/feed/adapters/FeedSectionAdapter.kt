package com.example.lovertips.home.feed.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.home.feed.ui.model.SectionModel

import java.util.ArrayList

class FeedSectionAdapter(val context: Context, private val sectionModelArrayList: ArrayList<SectionModel>
) : RecyclerView.Adapter<FeedSectionAdapter.FeedViewHolder>() {


    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var itemRecyclerView: RecyclerView
        val sectionLabel: TextView
//        val showAllButton: TextView


        init {
            // itemRecyclerView = itemView.findViewById(R.id.top_grid_recycler_view) as RecyclerView
            sectionLabel = itemView.findViewById(R.id.section_label) as TextView
//            showAllButton = itemView.findViewById(R.id.section_show_all_button) as TextView
            itemRecyclerView = itemView.findViewById(R.id.item_recycler_view) as RecyclerView
        }
    }

    override fun getItemCount(): Int {
        return sectionModelArrayList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sectioned_recycler_view, parent, false)
        return FeedViewHolder(
            view
        )
    }

    override  fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val sectionModel = sectionModelArrayList[position]

        //recycler view for items
        holder.sectionLabel.setText(sectionModel.sectionLabel)
        holder.itemRecyclerView.setHasFixedSize(true)
        holder.itemRecyclerView.setNestedScrollingEnabled(false)

        /* set layout manager on basis of recyclerview enum type */
        //val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        holder.itemRecyclerView.setLayoutManager(linearLayoutManager)

        val adapter = FeedAdapter(
            context,
            sectionModel.itemArrayList
        )
        holder.itemRecyclerView.adapter = adapter



    }


}