package com.demo.weightcard.utils

import android.text.method.KeyListener
import android.widget.EditText

fun EditText.setEditable(editable: Boolean) {
    if (editable) {
        if (this.keyListener == null) {
            this.keyListener = this.tag as KeyListener
        }
    } else {
        this.tag = this.keyListener
        this.keyListener = null
    }
}