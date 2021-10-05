package com.demo.weightcard.utils

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.demo.weightcard.logic.validation.EditTextFieldState
import com.demo.weightcard.logic.validation.updateWith

fun MutableLiveData<EditTextFieldState>.bind(
    lifecycleOwner: LifecycleOwner,
    editText: EditText
) {
    val textWatcher = editText.doAfterTextChanged { editable ->
        editText.error = ""
        updateWith { it.copy(value = editable.toString()) }
    }

    observe(lifecycleOwner) {
        editText.error = it.error
        if (editText.text.toString() != it.value) {
            editText.setText(it.value)
        }
    }

    lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                editText.removeTextChangedListener(textWatcher)
            }
        }
    })
}
