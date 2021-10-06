package com.demo.weightcard.ui.registartion.birthday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.model.Profile

class ScreenBirthdayViewModel(profile: Profile? = null) : RegistrationViewModelDelegate() {
    val birthday = MutableLiveData<Long>(profile?.birthday)

    override fun isAllFieldsValid(): Boolean {
        return birthday.value != null
    }

    override fun getAllLiveData(): List<LiveData<out Any>> {
        return listOf(birthday)
    }

}