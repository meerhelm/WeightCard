package com.demo.weightcard.model

import com.demo.weightcard.logic.Units
import com.demo.weightcard.logic.registration_flow.RegistrationStep
import com.demo.weightcard.logic.registration_flow.steps.BirthdayStep
import com.demo.weightcard.logic.registration_flow.steps.PhotoStep
import com.demo.weightcard.logic.registration_flow.steps.WeightStep

data class Profile(
    val id: Long,
    val weight: String = "",
    val birthday: Long = 0L,
    val units: String = Units.KG,
    val face: String = "",
) {
    val registrationFlow: List<RegistrationStep> = listOf(WeightStep(), BirthdayStep(), PhotoStep())
}