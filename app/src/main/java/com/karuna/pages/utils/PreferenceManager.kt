package com.karuna.pages.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.karuna.pages.data.entities.User

@SuppressLint("CommitPrefEdits")
class PreferenceManager(_context: Context) {

    var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    internal var PRIVATE_MODE = 0


    companion object {
        private val LOGIN_STATUS = "LOGIN_STATUS"
        private val USER_DATA = "user"
        private val USER_PASS = "password"
        private val PREF_NAME = "karuna_prefrences"
    }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    fun clearUser() {
        editor.clear()
        editor.commit()
    }

    fun setUserCredentials(password: String) {
        editor.putString(USER_PASS, password)
        editor.commit()
    }

    val password: String
    get() = pref.getString(USER_PASS, "") ?: ""

    fun setLoginStatus(status: Int) {
        editor.putInt(LOGIN_STATUS, status)
        editor.commit()
    }

    fun getLoginStatus(): Int {
        return pref.getInt(LOGIN_STATUS, 0)
    }

    var user: User?
        get() {
            val userString = pref.getString(USER_DATA, "") ?: ""
            return if (userString.isEmpty()) null else get(USER_DATA)
        }
        set(user) {
            put(user, USER_DATA)
        }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        pref.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = pref.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}