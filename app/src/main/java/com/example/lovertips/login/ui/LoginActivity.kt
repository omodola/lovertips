package com.example.lovertips.login.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lovertips.MainActivity
import com.example.lovertips.register.ui.RegisterActivity
import com.google.gson.Gson
import com.example.lovertips.R
import com.example.lovertips.login.PreferenceHelper
import com.example.lovertips.login.data.model.LoginRequest
import com.example.lovertips.login.ui.model.LoginViewModel
import com.example.lovertips.login.ui.model.LoginViewModelFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private var preferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferenceHelper = PreferenceHelper(this)


        //added this
        val username = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        //val checkBox = findViewById<CheckBox>(R.id.checkbox)
        val signin =findViewById<ImageButton>(R.id.signin)
        val signup = findViewById<Button>(R.id.signup)

        val loading = findViewById<ProgressBar>(R.id.loading)




        signin.setOnClickListener {
            val user = LoginRequest(
                username!!.text.toString(),
                password!!.text.toString()
            ) // object

            val userJson = Gson().toJson(user)
            loading.visibility = View.VISIBLE
            //preferenceHelper!!.putToken(response["token"].toString())
            loginViewModel.login(userJson)

        }


        loginViewModel = ViewModelProviders.of(this,
            LoginViewModelFactory()
        )
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            signin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                preferenceHelper!!.putIsLogin(true)
                 preferenceHelper!!.putToken(loginResult.success._token)
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                val user = LoginRequest(
                    username!!.text.toString(),
                    password!!.text.toString()
                ) // object

                val userJson = Gson().toJson(user)
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(userJson)
                }
                false
            }

            /*signin.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }*/
        }

        signup.setOnClickListener {

            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.userObject
        val firstName = model.firstName

        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $firstName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}


