package io.npc.support.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(applicationContext, resId, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String) = context?.toast(message)

fun Fragment.toast(@StringRes resId: Int) = context?.toast(resId)

fun ViewGroup.inflate(@LayoutRes res: Int): View {
    return LayoutInflater.from(context).inflate(res, this, false)
}
