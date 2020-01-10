package com.example.lovertips.register.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lovertips.MainActivity
import com.example.lovertips.R
import com.example.lovertips.login.ui.LoginActivity
import com.example.lovertips.login.PreferenceHelper
import com.example.lovertips.login.ui.afterTextChanged
import com.example.lovertips.register.data.model.RegisterRequest
import com.example.lovertips.register.ui.model.RegisterViewModel
import com.example.lovertips.register.ui.model.RegisterViewModelFactory
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
   // private var preferenceHelper: PreferenceHelper? = null
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val name = findViewById<EditText>(R.id.name);
        val checkBox = findViewById<CheckBox>(R.id.checkbox);
        val signin =findViewById<Button>(R.id.signin);
        val signup = findViewById<ImageButton>(R.id.signup);

        signup.setOnClickListener {

            /*val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)*/
        }

        signin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }*/
    internal var RegisterURL = "http://10.10.10.115/lovertips/api/users/"
   // private var etname: EditText? = null
    private var email:EditText?= null
    private var name:EditText? = null
    private var phone_number:EditText? = null
    private var password:EditText? = null
    private var confirmPassword:EditText? = null
    private var signin:Button? = null
    private var signup: ImageButton? = null
    private var chekcBox: TextView? = null
    private var preferenceHelper: PreferenceHelper? = null
    private val RegTask = 1
    //private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        preferenceHelper = PreferenceHelper(this)


        //added this
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        val signin =findViewById<Button>(R.id.signin)
        val signup = findViewById<ImageButton>(R.id.signup)

        val loading = findViewById<ProgressBar>(R.id.loading)

        signin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        signup.setOnClickListener {

            val user = RegisterRequest(
                email!!.text.toString(),
                password!!.text.toString(),
                confirmPassword!!.text.toString(),
                phoneNumber!!.text.toString()
            )
            val userJson = Gson().toJson(user)
            loading.visibility = View.VISIBLE
            registerViewModel.login(userJson)
        }

        registerViewModel = ViewModelProviders.of(this,
            RegisterViewModelFactory()
        ).get(RegisterViewModel::class.java)


        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it?: return@Observer
            // disable login button unless both username / password is valid
            signin.isEnabled = registerState.isDataValid

            if (registerState.emailError != null) {
                email.error = getString(registerState.emailError)
            }
            if (registerState.passwordError != null) {
                password.error = getString(registerState.passwordError)
            }
        })

        registerViewModel.registerResult.observe(this@RegisterActivity, Observer {
            val registerResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (registerResult.error != null) {
               // showRegisterFailed(registerResult.error)
            }
            if (registerResult.success != null) {
                preferenceHelper!!.putIsLogin(true)
                preferenceHelper!!.putToken(registerResult.success._token)
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                updateUiWithUser(registerResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        email.afterTextChanged {
            registerViewModel.registerDataChanged(
                email.text.toString(),
                password.text.toString(),
                confirmPassword.text.toString())
        }

    }

    private fun updateUiWithUser(model: RegisterUserView) {
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

//    private fun showRegisterFailed(@StringRes errorString: Int) {
//            Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
//        }




}