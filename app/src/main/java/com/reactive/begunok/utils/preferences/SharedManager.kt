package com.reactive.begunok.utils.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.reactive.begunok.network.User
import com.reactive.begunok.utils.preferences.PreferenceHelper.get
import com.reactive.begunok.utils.preferences.PreferenceHelper.set

class SharedManager(
    private val preferences: SharedPreferences,
    private val gson: Gson,
    private val context: Context
) {

    companion object {
        const val TOKEN = "TOKEN"
        const val USER = "USER"
    }

    var token: String
        get() = preferences[TOKEN, ""]
        set(value) {
            preferences[TOKEN] = value
        }

    var user: User
        get() = gson.fromJson(preferences[USER, ""], User::class.java)
        set(value) {
            preferences[USER] = gson.toJson(value)
        }

    fun deleteAll() {
        preferences.edit().clear().apply()
    }
}
