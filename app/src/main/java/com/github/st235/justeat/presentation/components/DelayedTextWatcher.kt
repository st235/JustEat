package com.github.st235.justeat.presentation.components

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher

abstract class DelayedTextWatcher(
    private val delayTimeInMillis: Long
): TextWatcher {

    private val handler = Handler(Looper.getMainLooper())
    private var lastKnownRunnable: Runnable? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // empty on purpose
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // empty on purpose
    }

    override fun afterTextChanged(s: Editable?) {
        val oldRunnable = lastKnownRunnable

        if (oldRunnable != null) {
            handler.removeCallbacks(oldRunnable)
        }

        val newRunnable = Runnable { onDelayedTextChange(s.toString(), delayTimeInMillis) }
        lastKnownRunnable = newRunnable

        handler.postDelayed(newRunnable, delayTimeInMillis)
    }

    abstract fun onDelayedTextChange(text: String?, delay: Long)

}