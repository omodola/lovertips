package com.example.lovertips.profile.providers

import com.example.lovertips.R
import com.example.lovertips.profile.model.ProfileViewModel


class ProfileData {
    private val date = arrayOf(
        "12 Nov", "13 Nov", "14 Nov",
        "15 Nov", "16 Nov", "17 Nov", "18 Nov", "19 Nov"
    )
    private val title = arrayOf(
        "Dola Olowe", "John Doe", "Maria Meray", "Dola Olowe", "John Doe",
        "Maria Meray", "ffd", "gdf"
    )
    private val content = arrayOf(
        "So you wont come to my apartment right", "mfdjnfj", "kellssdsgjdsghjkds",
        "mfdjnfj", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque imperdiet libero vitae quam tincidunt, " +
                "non efficitur velit consectetur. Suspendisse posuere, velit ac tristique scelerisque, augue sapien ullamcorper est," +
                " quis blandit massa magna nec sapien. Integer eget mi sit amet turpis convallis egestas. Proin sagittis at quam sed pulvinar." +
                " Vestibulum facilisis nisl ultricies tincidunt eleifend. Quisque quis auctor ipsum, et gravida felis." +
                " Aliquam ultrices sit amet urna eu tempor. " +
                "Sed purus ante, hendrerit non ullamcorper ac, pharetra ut augue. " +
                "Integer non lacus non sem scelerisque malesuada. Quisque luctus magna et orci rutrum molestie. " +
                "Fusce vitae sem lacinia urna sagittis laoreet nec a libero. Nulla facilisi.", "mfdjnfj", "khgj", "bjbj"
    )
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


    fun quick_links(): ArrayList<ProfileViewModel> {

//         val topR = ItemsModel(myTagList[j])
        val list = ArrayList<ProfileViewModel>()

        for (j in 0..5) {
            val topR = ProfileViewModel()

            topR.get_title = title[j]
            topR.get_content = content[j]
            topR.get_image = image[j]
            topR.get_date= date[j]
            //println(myTagList[j])
            list.add(topR)
            //list.add(topR.tagc)


        }
        //val topR = ItemsModel()

        return list
    }
}
