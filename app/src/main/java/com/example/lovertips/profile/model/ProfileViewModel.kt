package com.example.lovertips.profile.model


import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is Profile Fragment"
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

    var get_date: String = ""

        get() = field

        set(value) {
            field = value
        }
}