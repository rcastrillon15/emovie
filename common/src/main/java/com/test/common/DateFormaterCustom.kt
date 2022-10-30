package com.test.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.toDateYYYY() = SimpleDateFormat("yyyy-MM-dd").parse(this)
    ?.let { SimpleDateFormat("yyyy").format(it) }
