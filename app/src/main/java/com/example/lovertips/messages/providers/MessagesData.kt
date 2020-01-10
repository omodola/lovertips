package com.example.lovertips.messages.providers

import com.example.lovertips.R
import com.example.lovertips.messages.model.MessagesViewModel


class MessagesData {
    private val title = arrayOf("Dola Olowe", "John Doe", "Maria Meray","Dola Olowe", "John Doe",
        "Maria Meray", "ffd", "gdf")
    private val content  = arrayOf("So you wont come to my apartment right", "mfdjnfj", "kellssdsgjdsghjkds",
                                    "mfdjnfj", "kellssdsgjdsghjkds", "mfdjnfj", "khgj","bjbj")
    private val image = intArrayOf(
        R.drawable.profile_pix,
        R.drawable.ad1,
        R.drawable.profile_pix,
        R.drawable.ad,
        R.drawable.ad1,
        R.drawable.ad2,
        R.drawable.ad,
        R.drawable.ad1,
        R.drawable.ad2
    )// R.drawable.home_grid_saved_items,


    fun quick_links(): ArrayList<MessagesViewModel> {

//         val topR = ItemsModel(myTagList[j])
        val list = ArrayList<MessagesViewModel>()

        for (j in 0..5) {
            val topR =
                MessagesViewModel()

            topR.get_title = title[j]
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