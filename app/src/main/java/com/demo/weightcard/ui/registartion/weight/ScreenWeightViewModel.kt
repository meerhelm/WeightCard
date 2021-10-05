package com.demo.weightcard.ui.registartion.weight

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.model.Profile

class ScreenWeightViewModel (profile: Profile? = null) : RegistrationViewModelDelegate() {
    override fun isAllFieldsValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllLiveData(): List<LiveData<out Any>> {
        TODO("Not yet implemented")
    }
}