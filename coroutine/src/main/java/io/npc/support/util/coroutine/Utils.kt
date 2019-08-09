package io.npc.support.util.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun <T> launchOnMainThread(
    api: suspend () -> T,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null,
    context: CoroutineContext = Dispatchers.Default
) {
    val handler = CoroutineExceptionHandler { _, e ->
        onError?.invoke(e)
    }

    CoroutineScope(Dispatchers.Main).launch(handler) {
        withContext(context) { api() }.let {
            onSuccess?.invoke(it)
        }
    }
}
