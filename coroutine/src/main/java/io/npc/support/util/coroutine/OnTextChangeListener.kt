package io.npc.support.util.coroutine

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.*

class OnTextChangeListener(
    private val afterTextChanged: (s: Editable?) -> Unit,
    private val beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
    private val onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null,
    private val timeMillis: Long = 200
) : TextWatcher {

    private var searchFor = ""

    private var textChangedJob: Job? = null

    override fun afterTextChanged(s: Editable?) {
        val searchText = s.toString().trim()
        if (searchText != searchFor) {
            searchFor = searchText

            textChangedJob?.cancel()
            textChangedJob = CoroutineScope(Dispatchers.Main).launch {
                delay(timeMillis)
                if (searchText == searchFor) {
                    afterTextChanged.invoke(s)
                }
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }
}
