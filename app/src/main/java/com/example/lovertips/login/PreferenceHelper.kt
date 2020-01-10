package com.example.lovertips.login

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(private val context: Context) {

    private val INTRO = "intro"
    private val NAME = "name"
    private val HOBBY = "hobby"
    private val EMAIL = "email"
    private val TOKEN = "token"

    private val app_prefs: SharedPreferences

    init {
        app_prefs = context.getSharedPreferences(
            "shared",
            Context.MODE_PRIVATE
        )
    }

    fun putIsLogin(loginorout: Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(INTRO, loginorout)
        edit.apply()
    }

    fun getIsLogin(): Boolean {
        return app_prefs.getBoolean(INTRO, false)
    }

    fun putName(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(NAME, loginorout)
        edit.apply()
    }

    fun getNames(): String? {
        return app_prefs.getString(NAME, "")
    }

    fun putHobby(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(HOBBY, loginorout)
        edit.apply()
    }

    fun getHobbys(): String? {
        return app_prefs.getString(HOBBY, "")
    }

    fun putEmail(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(EMAIL, loginorout)
        edit.apply()
    }

    fun getEmail(): String? {
        return app_prefs.getString(EMAIL, "")
    }

    fun putToken(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(TOKEN, loginorout)
        edit.apply()
    }

    fun getToken(): String? {
        return app_prefs.getString(TOKEN, "")
    }
}