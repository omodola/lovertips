package com.example.lovertips.home.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.home.feed.adapters.FeedAdapter
import com.example.lovertips.home.feed.providers.FeedData
import com.example.lovertips.home.feed.ui.model.ItemModel
import com.example.lovertips.home.feed.ui.model.SectionModel
import com.example.lovertips.login.PreferenceHelper
import com.example.lovertips.posts.ui.model.PostViewModel
import com.example.lovertips.R
import com.example.lovertips.posts.ui.GetPostUserView
import kotlin.collections.ArrayList


class FeedFragment : Fragment() {

    private var preferenceHelper: PreferenceHelper? = null
    private lateinit var postViewModel: PostViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postViewModel =
                ViewModelProviders.of(this).get(PostViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_feed, container, false)
        preferenceHelper = PreferenceHelper(requireContext())

        val textView = getString(R.string.title_feed)
        (activity as AppCompatActivity).supportActionBar?.title = textView

        val getToken = preferenceHelper?.getToken().toString()

        postViewModel.getAllPosts(getToken)
        postViewModel.allPostsResult.observe(this, Observer {
            val postResult = it ?: return@Observer

            if (postResult.success != null)
            {
               val list = updateUiWithUser(postResult.success)
               setUpRecvcler(root, list)

            }
        })

        return root
    }


    private fun setUpRecvcler(rooti:View, datum:ArrayList<ItemModel>) {


        val recyclerView: RecyclerView = rooti.findViewById(R.id.feed_recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setLayoutManager(linearLayoutManager)

        val sectionModelArrayList = ArrayList<SectionModel>()
        //add the section and items to array list
        /*val data = FeedData()

        val listViewAdapter = FeedAdapter(
            requireActivity(),
            data.card_data()
        )
        recyclerView.adapter = listViewAdapter*/

        val listViewAdapter = FeedAdapter(
            requireActivity(),
            datum
        )
        recyclerView.adapter = listViewAdapter

//        sectionModelArrayList.add(SectionModel("top rated", data.card_data()))
//        sectionModelArrayList.add(SectionModel(null, data.card_data()))
//        sectionModelArrayList.add(SectionModel(null, data.card_data()))
//        sectionModelArrayList.add(SectionModel(null, data.card_data()))
//        sectionModelArrayList.add(SectionModel(null, data.card_data()))
//        sectionModelArrayList.add(SectionModel(null, data.card_data()))
//        sectionModelArrayList.add(SectionModel(null, data.card_data()))
//
//        val adapter = ExampleSectionAdapter(
//            requireActivity(),
//            sectionModelArrayList
//        )
//        recyclerView.adapter = adapter

    }




    private fun updateUiWithUser(model: GetPostUserView): ArrayList<ItemModel> {
        val list = ArrayList<ItemModel>()
        val array = model._array

        for (i in 0 until array.length()){
            val data = array.getJSONObject(i)
            val topR = ItemModel()
println(data)
            val user = data.getJSONObject("user")
            val firstName = user.getString("first_name")

            val profilePhoto = user.getJSONObject("profile_photo")
            val url = profilePhoto.getString("url")
            val title = data.getString("title")
            topR.gettitle = title
            topR.profile_image = R.drawable.profile_pix
            topR.gettag = data.getString("created_on")
            topR.getcontent = data.getString("body")

            /*if (url.isNotEmpty()){
                topR.image_drawable =  R.drawable.profile_pix
            }*/
            list.add(topR)


        }
        return list
    }

    //    private fun setUpSectionedLinksView(rooti:View) {
//
//
//        val recyclerView: RecyclerView = rooti.findViewById(R.id.feed_sectioned_recycler_view)
//        recyclerView.setHasFixedSize(true)
//
//        val linearLayoutManager = LinearLayoutManager(requireActivity())
//        recyclerView.setLayoutManager(linearLayoutManager)
//
//        val sectionModelArrayList = ArrayList<SectionModel>()
//        //add the section and items to array list
//        val data = QuickLinksData()
//
//        sectionModelArrayList.add(SectionModel("top rated", data.quick_links()))
//        sectionModelArrayList.add(SectionModel(null, data.quick_links()))
//
//        val adapter = SectionedAdapter(
//            requireActivity(),
//            sectionModelArrayList
//        )
//        recyclerView.adapter = adapter
//
//    }
}