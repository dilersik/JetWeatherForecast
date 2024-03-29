package com.example.jetweatherforecast.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Int.formatDate(): String {
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    val date = Date(this.toLong() * 1000)

    return sdf.format(date)
}

fun Int.formatDateTime(): String {
    val sdf = SimpleDateFormat("hh:mm:aa", Locale.getDefault())
    val date = Date(this.toLong() * 1000)

    return sdf.format(date)
}

fun Double.formatDecimals() = " %.0f".format(this)
