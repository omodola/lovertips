package com.example.lovertips.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewCommunicator : ViewModel()
{
    val message = MutableLiveData<Any>()


    fun setMsgCommunicator(msg:String){
        message.setValue(msg)
    }
}