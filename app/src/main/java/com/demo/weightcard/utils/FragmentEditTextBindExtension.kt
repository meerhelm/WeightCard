package com.demo.weightcard.utils

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

fun Fragment.bindEditTextToViewModel(liveData: MutableLiveData<String>?, editText: EditText) {
    liveData?.observe(viewLifecycleOwner) {
        if (it != editText.text.toString()) {
            editText.setText(it)
        }
    }
    editText.addTextChangedListener {
        liveData?.value = it?.toString()
    }
}