package com.example.lovertips.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lovertips.home.broadcast.BroadcastFragment
import com.example.lovertips.home.feed.ui.FeedFragment
import com.example.lovertips.messages.MessagesFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    override fun getItem(position: Int) : Fragment{
        return when (position) {
            0 -> {
                FeedFragment()
            }
            else -> {
                return BroadcastFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Feeds"
            else -> {
                return "Broadcast"
            }
        }
    }
}


