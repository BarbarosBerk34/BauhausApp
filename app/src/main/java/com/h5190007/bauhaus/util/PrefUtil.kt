package com.h5190007.bauhaus.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PrefUtil {
    private fun getPrefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getStringPref(context: Context, key: String?): String? {
        return getPrefs(context).getString(key, null)
    }

    fun setStringPref(context: Context, key: String?, value: String?) {
        getPrefs(context).edit().putString(key, value).apply()
    }
}