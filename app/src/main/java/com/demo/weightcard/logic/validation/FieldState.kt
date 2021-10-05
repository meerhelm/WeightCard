package com.demo.weightcard.logic.validation

import androidx.lifecycle.MutableLiveData

data class FieldState<T>(
    val validator: Validator<T>? = null,
    val value: T? = null,
    val error: String? = null
)

typealias EditTextFieldState = FieldState<String>

fun <T> MutableLiveData<FieldState<T>>.updateWith(builder: (field: FieldState<T>) -> FieldState<T>) {
    this.value = builder(this.value ?: return)
}