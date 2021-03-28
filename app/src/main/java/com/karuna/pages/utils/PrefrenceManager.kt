package com.karuna.pages.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.karuna.pages.data.entities.User

@SuppressLint("CommitPrefEdits")
class PrefrenceManager(private var _context: Context) {

    var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    internal var PRIVATE_MODE = 0


    companion object {

        private val LOGIN_STATUS = "LOGIN_STATUS"
        private val PROFILE = "security_user_profilellll"
        private val PASSWORD = "security_user_password"
        private val USER_DATA = "user"
        private val CONFIG_TAKE_PICTURE = "CONFIG_TAKE_PICTURE"
        private val CONFIG_DO_BOTH_TASKS = "CONFIG_DO_BOTH_TASKS"
        private val CONFIG_VERIFY_ID_PNLINE = "CONFIG_VERIFY_ID_PNLINE"
        private val CONFIG_VERIFY_BY_SCANNING = "VERIFY_BY_SCANNING"
        private val CONFIG_VISITOR_SHOULD_HAVE_APPOINTMENT = "VISITOR_SHOULD_HAVE_APPOINTMENT"
        private val IS_CONFIGURED = "IS_CONFIGURED"
        private val PREF_NAME = "security_prefrencesllll"

    }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    fun clearUser() {
        editor.clear()
        editor.commit()
    }

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
//            user?.safebodaAccessToken?.let(::setSafebodaAccessToken)
//            editor.putString(USER_DATA, gson.toJson(user, User::class.java)).apply()
           // put(USER_DATA, user!!)
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