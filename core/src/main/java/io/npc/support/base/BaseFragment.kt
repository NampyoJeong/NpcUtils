package io.npc.support.base

import android.content.Intent
import androidx.fragment.app.Fragment
import io.npc.support.utils.ActivityResultDelegate
import io.npc.support.utils.ActivityResultManager

abstract class BaseFragment : Fragment() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        ActivityResultManager.popActivityRequest(requestCode)?.run {
            invoke(requestCode, resultCode, data)
        } ?: run {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    protected fun startActivityForResult(intent: Intent, requestCode: Int, onActivityResult: ActivityResultDelegate? = null) {
        onActivityResult?.let {
            ActivityResultManager.putActivityRequest(requestCode, it)
        }
        startActivityForResult(intent, requestCode)
    }
}
