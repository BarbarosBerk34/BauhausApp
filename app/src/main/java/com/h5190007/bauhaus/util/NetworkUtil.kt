package com.h5190007.bauhaus.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun checkNetworkConn(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
    }
}