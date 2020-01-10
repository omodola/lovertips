package com.example.lovertips.login.ui.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.lovertips.login.data.LoginRepository
import com.github.kittinunf.result.Result

import com.example.lovertips.R
import com.example.lovertips.login.ui.LoggedInUserView
import com.example.lovertips.login.ui.LoginFormState
import com.example.lovertips.login.ui.LoginResult
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import org.json.JSONObject

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(userJson:String) {
        // can be launched in a separate asynchronous job
        //val result = loginRepository.login(username)
        try {
            //Fuel.post("http://192.168.56.1/lovertips/api/login/")

           Fuel.post("http://192.168.10.40/lovertips/api/login/")
            .body(userJson)
            .responseJson()
            {
                    request,response, result ->

                when(result){

                    is Result.Failure->{

                        // return LoginResult()
                        //Toast.makeText(this@LoginActivity, response.responseMessage, Toast.LENGTH_SHORT).show()
                        Log.i("ErrorMsg", result.getException().toString())
                        _loginResult.value =
                            LoginResult(error = R.string.login_failed)
                        // return LoginResult(error= R.string.login_failed)
                    }
                    is Result.Success ->{

                        val jsono = result.get().obj()
                        val data = JSONObject(jsono.toString())
                        val token = response["token"].toString().drop(1).dropLast(1)
                        _loginResult.value =
                            LoginResult(
                                success = LoggedInUserView(
                                    userObject = data,
                                    firstName = data.getString("first_name"), _token = token
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

//        when(result){
//
//            is Result.Failure->{
//
//                // return LoginResult()
//                //Toast.makeText(this@LoginActivity, response.responseMessage, Toast.LENGTH_SHORT).show()
//                Log.i("ErrorMsg", result.getException().toString())
//                _loginResult.value = LoginResult(error = R.string.login_failed)
//               // return LoginResult(error= R.string.login_failed)
//            }
//            is Result.Success ->{
//
//                val jsono = result.get().obj()
//                val data = JSONObject(jsono.toString())
//                _loginResult.value =
//                    LoginResult(success = LoggedInUserView(userObject = data, firstName = data.getString("first_name") ))
//                //return _loginResult
//            }
//        }
    }

//    fun onTaskCompleted(result: Result<FuelJson, FuelError>): LoginResult {
//
//        when(result){
//
//            is Result.Failure->{
//
//               // return LoginResult()
//                //Toast.makeText(this@LoginActivity, response.responseMessage, Toast.LENGTH_SHORT).show()
//                Log.i("ErrorMsg", result.getException().toString())
//                return LoginResult(error= R.string.login_failed)
//            }
//            is Result.Success ->{
//
//                val jsono = result.get().obj()
//                val data = JSONObject(jsono.toString())
//
//                return LoginResult(LoggedInUserView(data))
//
//
//            }
//        }
//    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
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
