package com.example.lovertips.register.ui.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.github.kittinunf.result.Result

import com.example.lovertips.R
import com.example.lovertips.register.data.RegisterRepository
import com.example.lovertips.register.ui.RegisterErrorView
import com.example.lovertips.register.ui.RegisterFormState
import com.example.lovertips.register.ui.RegisterResult
import com.example.lovertips.register.ui.RegisterUserView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject

class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun login(userJson:String) {
        // can be launched in a separate asynchronous job
        //val result = loginRepository.login(username)
        try {
            Fuel.post("https://192.168.43.84/lovertips/api/users/")

                //Fuel.post("http://10.10.10.115/lovertips/api/users/")
                .body(userJson)
                .responseJson()
                { request, response, result ->

                    when (result) {

                        is Result.Failure -> {
                            Log.i("ErrorMsg", result.getException().toString())
                            println(response.data)
//                            _registerResult.value =
//                                RegisterResult(error =  RegisterErrorView(
//                                    userObject = data,
//                                    firstName = data.getString("first_name"), _token = token))
                        }
                        is Result.Success -> {

                            val jsono = result.get().obj()
                            val data = JSONObject(jsono.toString())
                            val token = response["token"].toString().drop(1).dropLast(1)
                            _registerResult.value =
                                RegisterResult(
                                    success = RegisterUserView(
                                        userObject = data,
                                        firstName = data.getString("first_name"), _token = token
                                    )
                                )
                        }
                    }
                }
        } catch (e: Exception) {

        } finally {

        }
    }


    fun registerDataChanged(email: String, password: String, confirmPassword: String) {
        if (!isEmailValid(email)) {
            _registerForm.value =
                RegisterFormState(emailError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value =
                RegisterFormState(passwordError = R.string.invalid_password)
        } else if(password != confirmPassword){
            _registerForm.value =
                RegisterFormState(passwordError = R.string.confirm_password)
        } else {
            _registerForm.value =
                RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isEmailValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 8
    }


}
