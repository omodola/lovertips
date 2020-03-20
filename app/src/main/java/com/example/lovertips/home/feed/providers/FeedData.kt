package com.example.lovertips.home.feed.providers


import com.example.lovertips.R
import com.example.lovertips.home.feed.ui.model.ItemModel
import com.example.lovertips.posts.ui.model.PostViewModel


class FeedData {

//    val gridViewString: IntArray = intArrayOf(10, 20, 30, 40, 50)


    private val gridViewString = arrayOf("Dola Olowe", "Selling", "Near Me")
    private val gridViewImageId: IntArray = intArrayOf(
        R.drawable.profile_pix,
        R.drawable.profile_pix,
        R.drawable.profile_pix
    )
    private val gridViewTag = arrayOf("12th Nov", "Selling", "Near Mye")
    private val gridViewContent = arrayOf("The exhausted wing of a sounded out page reversed the void in the" +
            " grass into a bee, a tiny kite the rain keeps learning. This is the story of recording the wars in a storm for a whole human hymn", "Selling", "Near Mye")

    private val gridViewContentImage: IntArray = intArrayOf(
        R.drawable.profile_pix,
        R.drawable.profile_pix,
        R.drawable.profile_pix
    )
    fun card_data(): ArrayList<ItemModel> {

//         val topR = ItemsModel(myTagList[j])
        val list = ArrayList<ItemModel>()

        for (j in 0..2) {
            val topR = ItemModel()

            topR.gettitle = gridViewString[j]
            topR.profile_image = gridViewImageId[j]
            topR.gettag =  gridViewTag[j]
            topR.getcontent =  gridViewContent[j]
            topR.post_image =  gridViewContentImage[j]

            //println(myTagList[j])
            list.add(topR)
            //list.add(topR.tagc)


        }
        //val topR = ItemsModel()

        return list
    }
}