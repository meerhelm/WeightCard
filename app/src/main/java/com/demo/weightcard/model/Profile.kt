package com.demo.weightcard.model

import android.graphics.Bitmap
import com.demo.weightcard.logic.registration_flow.RegistrationStep
import com.demo.weightcard.logic.registration_flow.steps.BirthdayStep
import com.demo.weightcard.logic.registration_flow.steps.PhotoStep
import com.demo.weightcard.logic.registration_flow.steps.WeightStep

data class Profile(
    val id: Long,
    val weight: String? = null,
    val birthday: String? = null,
    val face: Bitmap? = null, //pretty discussion question if use there bitmap or link to file
) {
    val registrationFlow: List<RegistrationStep> = listOf(WeightStep(), BirthdayStep(), PhotoStep())
}