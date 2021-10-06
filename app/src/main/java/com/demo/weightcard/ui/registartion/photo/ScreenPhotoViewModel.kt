package com.demo.weightcard.ui.registartion.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.model.Profile

class ScreenPhotoViewModel(profile: Profile? = null) : RegistrationViewModelDelegate() {
    val face = MutableLiveData<String>()

    init {
        profile?.face.let {
            face.value = it
        }
    }

    override fun getAllLiveData(): List<LiveData<out Any>> {
        return listOf(face)
    }
}