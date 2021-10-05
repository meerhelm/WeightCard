package com.demo.weightcard.logic.registration_flow

import com.demo.weightcard.logic.registration_flow.steps.*

class RegistrationStepProvider {
    val registrationSteps = listOf(
        WeightStep(),
        BirthdayStep(),
        PhotoStep(),
    )

    fun getStepByName(registrationStepName: String): RegistrationStep? =
        registrationSteps.find { it.stepName == registrationStepName }
}