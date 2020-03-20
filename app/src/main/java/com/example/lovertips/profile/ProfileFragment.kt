package com.example.lovertips.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.profile.adapters.ProfileAdapter
import com.example.lovertips.profile.model.ProfileViewModel
import com.example.lovertips.profile.providers.ProfileData
import java.nio.channels.Selector


class ProfileFragment : Fragment(), ProfileAdapter.OnClickProfile {


    private lateinit var profileViewModel: ProfileViewModel
    private var model: ProfileViewCommunicator?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
       // val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)




        val recyclerView: RecyclerView = root.findViewById(R.id.profile_recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setLayoutManager(linearLayoutManager)

        val data2 = ProfileData()
        val listViewAdapter =
            ProfileAdapter(
                requireActivity(),
                data2.quick_links(),
                this
            )
        recyclerView.adapter = listViewAdapter



        return root
    }


    override fun onItemClicked(item: ProfileViewModel, position: Int) {
        if(position == 0){
            val intent = Intent(activity, ProfileInformationActivity::class.java)
            activity?.startActivity(intent)
        }
        //model= ViewModelProviders.of(activity!!).get(ProfileViewCommunicator::class.java)

        //set the message to share to another fragment
       // model!!.setMsgCommunicator(edt.text.toString())
        //Launch the data receiver fragment

    }

}