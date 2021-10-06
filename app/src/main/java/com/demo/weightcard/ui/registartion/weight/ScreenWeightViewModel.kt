package com.demo.weightcard.ui.registartion.weight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.model.Profile

class ScreenWeightViewModel(profile: Profile? = null) : RegistrationViewModelDelegate() {
    val weight = MutableLiveData<String>(profile?.weight)
    val units = MutableLiveData<String>(profile?.units)

    override fun getAllLiveData(): List<LiveData<out Any>> {
        return listOf(weight, units)
    }
}