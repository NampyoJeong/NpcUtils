package io.npc.support.utils

import android.content.Intent
import android.util.SparseArray

typealias ActivityResultDelegate = (requestCode: Int, resultCode: Int, data: Intent?) -> Unit

object ActivityResultManager {
    private val activityRequests = SparseArray<ActivityResultDelegate>()

    fun putActivityRequest(requestCode: Int, delegate: ActivityResultDelegate) {
        activityRequests.put(requestCode, delegate)
    }

    fun popActivityRequest(requestCode: Int): ActivityResultDelegate? {
        return activityRequests.get(requestCode)?.apply {
            activityRequests.remove(requestCode)
        }
    }
}
