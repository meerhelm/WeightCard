package com.demo.weightcard.logic.validation

import androidx.lifecycle.MutableLiveData

class ValidationProcessor<T>(private vararg val fields: MutableLiveData<FieldState<T>>) {
    fun isAllFieldsValid(): Boolean {
        fields.forEach {
            val fieldState = it.value
            if (!isFieldValid(fieldState)) {
                return false
            }
        }
        return true
    }

    fun fillByErrors() {
        fields.forEach {
            val fieldState = it.value
            val error = fieldState?.validator?.validate(fieldState.value)
            if (error != null) {
                it.updateWith { it.copy(error = error) }
            }
        }
    }

    private fun isFieldValid(fieldState: FieldState<T>?): Boolean {
        return fieldState?.validator?.validate(fieldState.value) == null
    }
}