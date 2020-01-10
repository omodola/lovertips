package com.example.lovertips.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.lovertips.R
import com.example.lovertips.login.PreferenceHelper
import com.example.lovertips.posts.data.model.CreatePostRequest
import com.example.lovertips.posts.ui.model.PostViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson


class PostFragment : Fragment() {

    private var postTextInput:TextInputEditText?= null
    private lateinit var postViewModel: PostViewModel
    private var preferenceHelper: PreferenceHelper? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postViewModel =
            ViewModelProviders.of(this).get(PostViewModel::class.java)

        val root =  inflater.inflate(R.layout.fragment_post, container, false)
        val toolbar:Toolbar = root.findViewById(R.id.toolbar_post)

        val title:EditText = root.findViewById(R.id.create_post_title)
        val body:TextInputEditText = root.findViewById(R.id.post_text_input)

        preferenceHelper = PreferenceHelper(requireContext())

        val createPost:Button = root.findViewById(R.id.create_post)


        createPost.setOnClickListener {
            val postRequest = CreatePostRequest(
                title.text.toString(),
                body.text.toString()
            )


            val userJson = Gson().toJson(postRequest)

            if (preferenceHelper!!.getIsLogin()) {

                val token = preferenceHelper?.getToken().toString()
                postViewModel.createPost(userJson, token)
            }

            Toast.makeText(
                requireContext(),
                "$title +created succesfully",
                Toast.LENGTH_LONG
            ).show()

        }


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener(View.OnClickListener { activity!!.onBackPressed() })
        return root
    }
}