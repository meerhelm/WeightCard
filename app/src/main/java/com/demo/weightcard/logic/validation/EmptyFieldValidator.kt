package com.demo.weightcard.logic.validation

import android.content.Context
import com.demo.weightcard.R

class EmptyFieldValidator<T>(private val context: Context) : Validator<T> {
    override fun validate(value: T?): String? {
        if (value == null || value is String? && value.isEmpty()) {
            return context.getString(R.string.field_must_not_be_empty)
        }
        return null
    }
}