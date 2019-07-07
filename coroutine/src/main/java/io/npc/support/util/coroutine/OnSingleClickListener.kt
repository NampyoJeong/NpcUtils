package io.npc.support.util.coroutine

import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay

class OnSingleClickListener(private val timeMillis: Long = 500, private val click: (view: View?) -> Unit) : View.OnClickListener {

    private val actor = GlobalScope.actor<View?>(Dispatchers.Main) {
        for (event in channel) {
            click(event)
            delay(timeMillis)
        }
    }

    override fun onClick(v: View?) {
        actor.offer(v)
    }
}
