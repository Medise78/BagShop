package com.mahdi.snickersshop.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineExceptionHandler
import java.text.SimpleDateFormat
import java.util.*

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.v("error", "Error" + throwable.message)
}

fun priceStyle(oldPrice: String, currency: String): String {
    if (oldPrice.length > 3) {
        val reversed = oldPrice.reversed()
        var newPrice = ""
        for (i in oldPrice.indices) {
            newPrice += reversed[i]
            if ((i + 1) % 3 == 0) newPrice += ','
        }
        val finalString = newPrice.reversed()
        if (finalString.first() == ',') return "${finalString.substring(1)} $currency"
    }
    return "$oldPrice $currency"
}

fun timeStyle(timeInMillis: Long): String {
    val timeFormat = SimpleDateFormat("yyyy/mm/dd HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    return timeFormat.format(calendar.time)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showInternetToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}