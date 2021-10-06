package com.demo.weightcard.logic.registration_flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class RegistrationViewModelDelegate : ViewModel() {
    open fun isAllFieldsValid(): Boolean {
        return getAllLiveData()
            .filterIsInstance<MutableLiveData<String>>()
            .none { it.value?.isEmpty() == true }
    }

    abstract fun getAllLiveData(): List<LiveData<out Any>>
}