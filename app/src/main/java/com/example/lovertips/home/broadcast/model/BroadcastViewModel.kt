package com.example.lovertips.home.feed.ui.model

import androidx.lifecycle.ViewModel

class BroadcastViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text


    var get_title: String = ""

        get() = field

        set(value) {
            field = value
        }

    var get_image: Int = 0

        get() = field

        set(value) {
            field = value
        }

    var get_time: String = ""

        get() = field

        set(value) {
            field = value
        }


    var get_content: String = ""

        get() = field

        set(value) {
            field = value
        }
}