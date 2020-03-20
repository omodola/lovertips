package com.example.lovertips.profile.providers

import com.example.lovertips.R
import com.example.lovertips.profile.model.ProfileViewModel


class ProfileData {
    private val date = arrayOf(
        "12 Nov", "13 Nov", "14 Nov",
        "15 Nov", "16 Nov", "17 Nov", "18 Nov", "19 Nov"
    )
    private val title = arrayOf(
        "Dola Olowe", "Posts", "Comments"
    )
    private val content = arrayOf(
        "Personal Information", "Posts", "Comments")
    private val image = intArrayOf(
        R.drawable.user_icon,
        R.drawable.rss,
        R.drawable.mail_icon

    )// R.drawable.home_grid_saved_items,


    fun quick_links(): ArrayList<ProfileViewModel> {

//         val topR = ItemsModel(myTagList[j])
        val list = ArrayList<ProfileViewModel>()

        for (j in 0..2) {
            val topR = ProfileViewModel()

            topR.get_content = content[j]
            topR.get_image = image[j]
            //println(myTagList[j])
            list.add(topR)
            //list.add(topR.tagc)


        }
        //val topR = ItemsModel()

        return list
    }
}
