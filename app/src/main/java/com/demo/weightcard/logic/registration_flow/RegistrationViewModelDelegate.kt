package com.demo.weightcard.logic.registration_flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class RegistrationViewModelDelegate : ViewModel() {
    abstract fun isAllFieldsValid(): Boolean
    abstract fun getAllLiveData(): List<LiveData<out Any>>
}