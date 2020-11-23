package com.drew2u.getpsyched

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun alertDialog(title: String, message: String, buttonText: String, ctx: Context) {
    with (AlertDialog.Builder(ctx)) {
        setTitle(title)
        setMessage(message)
        setPositiveButton(buttonText, null)
        show()
    }
}