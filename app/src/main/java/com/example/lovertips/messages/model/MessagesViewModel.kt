package com.example.lovertips.messages.model

import androidx.lifecycle.ViewModel

class MessagesViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
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

    var get_content: String = ""

        get() = field

        set(value) {
            field = value
        }
}