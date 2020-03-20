package com.example.lovertips.home.broadcast.providers


import com.example.lovertips.R
import com.example.lovertips.home.feed.ui.model.BroadcastViewModel
import com.example.lovertips.messages.model.MessagesViewModel


class BroadcastData {
    private val title = arrayOf("Dola Olowe", "John Doe", "Maria Meray","Dola Olowe", "John Doe",
        "Maria Meray", "ffd", "gdf","ertfyg","etry","4rtyui","ertyuhjk","yetufyguhkj")
    private val content  = arrayOf("So you wont come to my apartment right" +
            "you wont come to my apartment rightyou wont come to my apartment right", "mfdjnfj", "kellssdsgjdsghjkds",
        "mfdjnfj", "kellssdsgjdsghjkds", "mfdjnfj", "khgj","bjbj","dgfhjkjbhvgcdfsgadfg",
        "tiyouiupoipo'åpkojihugyftufyiguhijkäl", "glhkjljhgftdrsdtfghjkj")
    private val image = intArrayOf(
        R.drawable.profile_pix,
        R.drawable.ad1,
        R.drawable.profile_pix,
        R.drawable.ad,
        R.drawable.ad1,
        R.drawable.ad2,
        R.drawable.ad,
        R.drawable.ad1,
        R.drawable.ad2,
        R.drawable.ad2,
        R.drawable.ad2,
        R.drawable.ad2,
        R.drawable.ad2




    )
    private val date  = arrayOf("10 Nov 2019", "11 Oct 2018", "14 April 2019",
        "11 Jan 2018", "05 Mar 2020", "06 Jun 2010", "16 Jul 2019","23 Dec 2020","24 Dec 2020","25 Dec 2020","26 Dec 2020")

    fun quick_links(): ArrayList<BroadcastViewModel> {

//         val topR = ItemsModel(myTagList[j])
        val list = ArrayList<BroadcastViewModel>()

        for (j in 0..10) {
            val topR =
                BroadcastViewModel()

            topR.get_title = title[j]
            topR.get_content = content[j]
            topR.get_image = image[j]
            topR.get_time = date[j]

            //println(myTagList[j])
            list.add(topR)
            //list.add(topR.tagc)


        }
        //val topR = ItemsModel()

        return list
    }
}