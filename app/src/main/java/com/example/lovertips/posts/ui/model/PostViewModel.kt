package com.example.lovertips.posts.ui.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovertips.posts.ui.GetAllPostsResult
import com.example.lovertips.posts.ui.GetPostUserView
import com.example.lovertips.posts.ui.PostResult
import com.example.lovertips.posts.ui.PostUserView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject

class PostViewModel ():ViewModel(){

   private val _postResult = MutableLiveData<PostResult>()
    val postResult: LiveData<PostResult> = _postResult


    private val _allPostsResult = MutableLiveData<GetAllPostsResult>()
    val allPostsResult: LiveData<GetAllPostsResult> = _allPostsResult

    fun createPost(userJson:String, userToken:String) {
        //val result = postRepository.post(input)
        try {
            Fuel.post("http://192.168.10.40/lovertips/api/posts/")
            //Fuel.post("http://192.168.56.1/lovertips/api/posts/")
                .header("token", userToken)
                .body(userJson)
                .responseJson()
                {
                        request,response, result ->
                    println(request)
                    println(response)

                    when(result){

                        is Result.Failure->{

                            // return LoginResult()
                            //Toast.makeText(this@LoginActivity, response.responseMessage, Toast.LENGTH_SHORT).show()
                            Log.i("ErrorMsg", result.getException().toString())
//                            _loginResult.value =
//                                LoginResult(error = R.string.login_failed)
                            // return LoginResult(error= R.string.login_failed)
                        }
                        is Result.Success ->{
                            println(result)

                            val jsono = result.get().obj()
                            val data = JSONObject(jsono.toString())
                            val token = response["token"].toString().drop(1).dropLast(1)
                            _postResult.value =
                                PostResult(
                                    success = PostUserView(
                                        user = data,
                                        title = data.getString("title"), _token = token
                                    )
                                )
                        }
                    }
                }
        } catch (e: Exception) {

        } finally {

        }
    }

    fun getAllPosts(token:String) {

        try {

            //Fuel.get("http://192.168.56.1/lovertips/api/posts/")
            Fuel.get("http://192.168.10.40/lovertips/api/posts/")

                .header("Content-Type", "application/json")
                .header("token", token)
                .responseJson()
                {
                        request,response, result ->
                    when(result){

                        is Result.Failure->{
                            Log.i("ErrorMsg", result.getException().toString())
                        }
                        is Result.Success ->{
                            val jsono = result.get().array()
                            _allPostsResult.value =
                                GetAllPostsResult(
                                    success = GetPostUserView(
                                        _array = jsono
                                    )
                                )
                        }
                    }
                }
        } catch (e: Exception) {

        } finally {

        }
    }

}