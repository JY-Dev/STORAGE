package com.example.storage.util

import java.text.SimpleDateFormat
import java.util.*

private fun Long.isToday() : Boolean = Date(this).getSimpleFormat() == Date(System.currentTimeMillis()).getSimpleFormat()
private fun Long.isYesterday() : Boolean = Date(this).getSimpleFormat() == Date(System.currentTimeMillis()+(1000*60*60*24*-1)).getSimpleFormat()

fun Date.getImageDateFormat() : String{
    return when {
        time.isToday() -> "오늘"
        time.isYesterday() -> "어제"
        else -> getSimpleFormat()
    }
}

fun Date.getSimpleFormat() : String = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(this)