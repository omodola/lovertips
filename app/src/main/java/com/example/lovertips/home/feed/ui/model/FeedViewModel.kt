package com.example.lovertips.home.feed.ui.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lovertips.R
import androidx.lifecycle.ViewModel
import com.example.lovertips.home.feed.ui.FeedResult
import com.example.lovertips.home.feed.ui.FeedUserView
import com.github.kittinunf.result.Result
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject

class FeedViewModel(): ViewModel(){

    private val _feedResult = MutableLiveData<FeedResult>()
    val feedResult: LiveData<FeedResult> = _feedResult

    fun getAllPosts(userToken:String){
        try {
            Fuel.get("http://192.168.56.1/lovertips/api/posts/")
                .responseJson()
                {
                    request,response, result ->
                    when(result){

                        is Result.Failure->{

                            // return LoginResult()
                            //Toast.makeText(this@LoginActivity, response.responseMessage, Toast.LENGTH_SHORT).show()
                            Log.i("ErrorMsg", result.getException().toString())
                            _feedResult.value =
                                FeedResult(error = R.string.login_failed)
                            // return LoginResult(error= R.string.login_failed)
                        }
                        is Result.Success ->{
                            val jsono = result.get().obj()
                            val data = JSONObject(jsono.toString())
                            val token = response["token"].toString().drop(1).dropLast(1)
                            _feedResult.value =
                                FeedResult(
                                    success = FeedUserView(
                                        userObject = data
                                        //firstName = data.getString("first_name"), _token = token
                                    )
                                )
                            //return _loginResult
                        }
                    }

                    //preferenceHelper!!.putToken(response["token"].toString())
                    //  loginViewModel.login(result)

                }
        } catch (e: Exception) {

        } finally {

        }
    }

}