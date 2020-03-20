package com.example.lovertips.home.broadcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.home.broadcast.providers.BroadcastData
import com.example.lovertips.home.feed.adapters.BroadcastAdapter


class BroadcastFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val textView = getString(R.string.app_name)
        (activity as AppCompatActivity).supportActionBar?.title = textView

        val root =  inflater.inflate(R.layout.fragment_broadcast, container, false)


        val recyclerView: RecyclerView = root.findViewById(R.id.broadcast_recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setLayoutManager(linearLayoutManager)

        val data2 = BroadcastData()
        val listViewAdapter =
            BroadcastAdapter(
                requireActivity(),
                data2.quick_links()
            )
        recyclerView.adapter = listViewAdapter
        return root
    }



}