package com.example.lovertips.home.feed.ui.model

import androidx.lifecycle.ViewModel

class ItemModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text


    var gettitle: String = ""

        get() = field

        set(value) {
            field = value
        }

    var image_drawable: Int = 0

        get() = field

        set(value) {
            field = value
        }

    var gettag: String = ""

        get() = field

        set(value) {
            field = value
        }


    var getcontent: String = ""

        get() = field

        set(value) {
            field = value
        }
}