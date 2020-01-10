package com.example.lovertips.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.lovertips.R
import com.example.lovertips.home.feed.ui.model.BroadcastViewModel

class ProfileFragment : Fragment() {


    private lateinit var homeViewModel: BroadcastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(BroadcastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        /*val hometoolbar: Toolbar = root.findViewById(R.id.toolbar_test)
        (activity as AppCompatActivity).setSupportActionBar(hometoolbar)*/

        val ctn = (activity as AppCompatActivity).supportActionBar
        ctn?.setDisplayShowTitleEnabled(false)


        /*val textView = getString(R.string.app_name)
        val mspan = SpannableString(textView);

        val mBlack = ForegroundColorSpan(Color.BLACK)
        val mRed = ForegroundColorSpan(Color.GREEN)

        mspan.setSpan(mRed, 5,9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        (activity as AppCompatActivity).supportActionBar?.title = mspan*/


        return root
    }


}