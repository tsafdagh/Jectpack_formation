package com.tsafack.jetpackformation.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedpreferencesHelper {

    companion object {

        private const val PREF_TIME = "Pref time"
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedpreferencesHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedpreferencesHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedpreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedpreferencesHelper()
        }
    }

    fun saveUpdateTime(time: Long) {
        prefs?.edit(commit = true) { putLong(PREF_TIME, time) }
    }

    fun getUpdateTime() = prefs?.getLong(PREF_TIME, 0)

    fun getCacheDuration() = prefs?.getString("pref_cache_duration", "")
}