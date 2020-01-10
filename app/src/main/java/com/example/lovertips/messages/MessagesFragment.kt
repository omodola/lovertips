package com.example.lovertips.messages

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.messages.adapters.MessagesAdapter
import com.example.lovertips.messages.model.MessagesViewModel
import com.example.lovertips.messages.providers.MessagesData

class MessagesFragment : Fragment() {

    private lateinit var messagesViewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messagesViewModel =
            ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        val messagestoolbar: Toolbar = root.findViewById(R.id.toolbar_messages)
        (activity as AppCompatActivity).setSupportActionBar(messagestoolbar)

        val textView = getString(R.string.title_messages)
        val mspan = SpannableString(textView);

        val mBlack = ForegroundColorSpan(Color.BLACK)
        val mRed = ForegroundColorSpan(Color.RED)

        mspan.setSpan(mRed, 1,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        (activity as AppCompatActivity).supportActionBar?.title = mspan


        val recyclerView: RecyclerView = root.findViewById(R.id.messages_recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setLayoutManager(linearLayoutManager)

        val data2 = MessagesData()
        val listViewAdapter =
            MessagesAdapter(
                requireActivity(),
                data2.quick_links()
            )
        recyclerView.adapter = listViewAdapter
        return root
    }

}