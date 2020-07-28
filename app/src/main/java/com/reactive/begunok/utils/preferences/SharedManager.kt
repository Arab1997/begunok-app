package com.reactive.begunok.utils.preferences

import android.content.Context
import android.content.SharedPreferences
import com.reactive.begunok.utils.preferences.PreferenceHelper.get
import com.reactive.begunok.utils.preferences.PreferenceHelper.set
import com.google.gson.Gson

class SharedManager(
    private val preferences: SharedPreferences,
    private val gson: Gson,
    private val context: Context
) {

    companion object {
        const val TOKEN = "TOKEN"
    }

    var token: String
        get() = preferences[TOKEN, ""]
        set(value) {
            preferences[TOKEN] = value
        }

    fun deleteAll() {
        preferences.edit().clear().apply()
    }
}
