package com.demo.weightcard.logic.registration_flow

import androidx.fragment.app.Fragment
import com.demo.weightcard.model.Profile

interface RegistrationStep {
    val stepName: String
    fun getStepFragment(): Fragment
    fun getViewModelDelegate(profile: Profile): RegistrationViewModelDelegate
}