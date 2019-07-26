package io.npc.support.util.coroutine

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay

@ObsoleteCoroutinesApi
class OnTextChangeListener(
    private val afterTextChanged: (s: Editable?) -> Unit,
    private val beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
    private val onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null,
    private val timeMillis: Long = 200
) : TextWatcher {

    private val actor = CoroutineScope(Dispatchers.Main).actor<Editable?> {
        for (event in channel) {
            afterTextChanged.invoke(event)
            delay(timeMillis)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        actor.offer(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }
}
