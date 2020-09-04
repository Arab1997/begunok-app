package com.reactive.begunok.utils.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reactive.begunok.network.User
import com.reactive.begunok.ui.adapters.EmailData
import com.reactive.begunok.utils.preferences.PreferenceHelper.get
import com.reactive.begunok.utils.preferences.PreferenceHelper.set

class SharedManager(
    private val preferences: SharedPreferences, private val gson: Gson
) {

    companion object {
        const val TOKEN = "TOKEN"
        const val USER = "USER"
        const val EMAILS = "EMAILS"
    }

    var token: String
        get() = preferences[TOKEN, ""]
        set(value) {
            preferences[TOKEN] = value
        }

    var user: User?
        get() = gson.fromJson(preferences[USER, ""], User::class.java)
        set(value) {
            preferences[USER] = gson.toJson(value)
        }

    var mails: List<EmailData>
        get() {
            val json = preferences.getString(EMAILS, "")
            return if (json == "") emptyList()
            else gson.fromJson(json, object : TypeToken<List<EmailData>>() {}.type)

        }
        set(value) {
            preferences[EMAILS] = gson.toJson(value)
        }

    fun deleteAll() {
        preferences.edit().clear().apply()
    }
}
