package io.npc.support.util.coroutine

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay

@ObsoleteCoroutinesApi
class OnSingleClickListener(private val timeMillis: Long = 500, private val click: (view: View?) -> Unit) : View.OnClickListener {

    private val actor = CoroutineScope(Dispatchers.Main).actor<View?> {
        for (event in channel) {
            click(event)
            delay(timeMillis)
        }
    }

    override fun onClick(v: View?) {
        actor.offer(v)
    }
}
