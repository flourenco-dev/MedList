package com.fabiolourenco.medlist.utils

import android.content.Context
import android.text.TextUtils

fun Context.isNotEmpty(string: String?) : Boolean {
    return !TextUtils.isEmpty(string)
}