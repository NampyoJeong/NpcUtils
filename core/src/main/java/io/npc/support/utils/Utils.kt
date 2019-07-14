package io.npc.support.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

fun Activity.requestPermission(permission: String, requestCode: Int, onSuccess: () -> Unit) {
    if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    } else {
        onSuccess()
    }
}

fun Fragment.requestPermission(permission: String, requestCode: Int, onSuccess: () -> Unit) {
    if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(arrayOf(permission), requestCode)
    } else {
        onSuccess()
    }
}

fun isGrantedPermission(grantResults: IntArray): Boolean {
    return grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED
}

fun String?.int(): Int = this?.toInt() ?: 0

fun String?.long(): Long = this?.toLong() ?: 0

fun String?.float(): Float = this?.toFloat() ?: 0.0f

fun String?.double(): Double = this?.toDouble() ?: 0.0
