package com.github.st235.justeat.presentation.utils

import android.view.View

fun <T: View> T.show(isShown: Boolean) {
    visibility = if (isShown) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
