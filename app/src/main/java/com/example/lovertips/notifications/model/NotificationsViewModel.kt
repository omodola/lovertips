package com.example.lovertips.notifications.model



import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is Profile Fragment"
//    }
//    val text: LiveData<String> = _text

    var get_title: String = ""

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

    var get_image: Int = 0

        get() = field

        set(value) {
            field = value
        }


}