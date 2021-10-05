package com.demo.weightcard.logic.registration_flow.steps

import androidx.fragment.app.Fragment
import com.demo.weightcard.logic.registration_flow.RegistrationStep
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.model.Profile
import com.demo.weightcard.ui.registartion.birthday.ScreenBirthday
import com.demo.weightcard.ui.registartion.birthday.ScreenBirthdayViewModel

class BirthdayStep : RegistrationStep {
    companion object {
        const val STEP_NAME = "birthday_step"
    }

    override val stepName: String
        get() = STEP_NAME

    override fun getStepFragment(): Fragment {
        return ScreenBirthday()
    }

    override fun getViewModelDelegate(profile: Profile): RegistrationViewModelDelegate {
        return ScreenBirthdayViewModel(profile)
    }
}