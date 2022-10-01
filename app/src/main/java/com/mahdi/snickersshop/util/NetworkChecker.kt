package com.mahdi.snickersshop.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkChecker(
    private val context: Context
) {
    val isNetworkConnected: Boolean
        @SuppressLint("ObsoleteSdkInt")
        get() {
            val result: Boolean
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val myNetwork =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } else {
                result = when (connectivityManager.activeNetworkInfo?.type) {
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    ConnectivityManager.TYPE_WIFI -> true
                    else -> false
                }
            }
            return result
        }
}