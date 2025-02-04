package com.example.roomdatabaseapp.utils

import android.util.Patterns
import android.widget.EditText

fun EditText.getString(): String {
    return this.text.toString().trim()
}

fun EditText.checkEmpty(): Boolean {
    return getString().isEmpty()
}
