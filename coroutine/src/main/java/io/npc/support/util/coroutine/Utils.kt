package io.npc.support.util.coroutine

import kotlinx.coroutines.*

fun <T> launchOnMainThread(
    api: suspend () -> T,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((e: Exception) -> Unit)? = null
) {
    CoroutineScope(Dispatchers.Main).launch {
        async { api() }.call(onSuccess, onError)
    }
}

suspend fun <T> Deferred<T>.call(
    onSuccess: ((T) -> Unit)? = null,
    onError: ((e: Exception) -> Unit)? = null
) {
    try {
        await().let { onSuccess?.invoke(it) }
    } catch (e: Exception) {
        onError?.invoke(e)
    }
}
