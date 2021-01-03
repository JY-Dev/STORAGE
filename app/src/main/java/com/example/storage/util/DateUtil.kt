package com.example.storage.util

import java.text.SimpleDateFormat
import java.util.*

fun isToday(date : Long) : Boolean =
    Date(date).getSimpleFormat() == Date(System.currentTimeMillis()).getSimpleFormat()


fun Date.getSimpleFormat() : String = SimpleDateFormat("yyyy-mm-dd", Locale.KOREA).format(this)