package com.example.lovertips.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.example.lovertips.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val textView = getString(R.string.app_name)
        (activity as AppCompatActivity).supportActionBar?.title = textView

        val root =  inflater.inflate(R.layout.fragment_home, container, false)
        val fragmentAdapter =
            HomePagerAdapter(childFragmentManager)

        val pager = root.findViewById(R.id.viewpager_main) as ViewPager
        pager.adapter = fragmentAdapter

        val tabLayout = root.findViewById<TabLayout>(R.id.tabs_main)
        tabLayout.setupWithViewPager(pager)

        return root

    }

}