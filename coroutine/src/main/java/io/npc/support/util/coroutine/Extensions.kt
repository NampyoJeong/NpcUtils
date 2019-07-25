package io.npc.support.util.coroutine

import android.view.View

fun View.setOnSingleClickListener(timeMillis: Long = 500, listener: (view: View?) -> Unit) {
    setOnClickListener(OnSingleClickListener(timeMillis, listener))
}
