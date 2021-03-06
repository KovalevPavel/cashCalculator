package com.github.restorantsnet.utils

import android.util.Log
import com.github.restorantsnet.BuildConfig

fun debugLog(string: String) {
    if (BuildConfig.DEBUG)
        Log.d("ZAWARUDO", string)
}