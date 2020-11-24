package com.drew2u.getpsyched

import android.content.Context
import androidx.appcompat.app.AlertDialog

/**
 * [AlertDialog] builder with one button and no listener.
 */
fun alertDialog(title: String, message: String, buttonText: String, ctx: Context) {
    with (AlertDialog.Builder(ctx)) {
        setTitle(title)
        setMessage(message)
        setPositiveButton(buttonText, null)
        show()
    }
}