package io.npc.support.util.coroutine

import android.text.Editable
import android.view.View
import android.widget.EditText

fun View.setOnSingleClickListener(timeMillis: Long = 500, listener: (view: View?) -> Unit) {
    setOnClickListener(OnSingleClickListener(timeMillis, listener))
}

fun EditText.setOnTextChangeListener(timeMillis: Long = 200, afterTextChanged: (s: Editable?) -> Unit) {
    addTextChangedListener(OnTextChangeListener(
        afterTextChanged = afterTextChanged,
        timeMillis = timeMillis
    ))
}
