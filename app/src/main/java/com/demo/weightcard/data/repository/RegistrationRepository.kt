package com.demo.weightcard.data.repository

import android.content.SharedPreferences
import com.demo.weightcard.logic.registration_flow.RegistrationStep
import com.demo.weightcard.logic.registration_flow.RegistrationStepProvider
import com.demo.weightcard.logic.registration_flow.steps.*

class RegistrationRepository(
    private val registrationStepProvider: RegistrationStepProvider,
    private val sharedPrefs: SharedPreferences
) {

    companion object {
        const val FLOW_STEPS_KEY = "flow_steps"
    }

    private fun getDefaultSteps(): List<String> {
        return listOf(
            WeightStep.STEP_NAME,
            BirthdayStep.STEP_NAME,
            PhotoStep.STEP_NAME,
        )
    }

    suspend fun getRegistrationFlow(): List<RegistrationStep> {
        return getRegistrationFlowSteps().mapNotNull { registrationStepProvider.getStepByName(it) }
    }

    fun getRegistrationFlowSteps(): List<String> {
        return sharedPrefs.getString(FLOW_STEPS_KEY, null)?.split(",") ?: getDefaultSteps()
    }

    fun setRegistrationFlowSteps(steps: List<String>) {
        sharedPrefs.edit().putString(FLOW_STEPS_KEY, steps.joinToString(",")).apply()
    }

    fun getAllAvailableSteps(): List<String> {
        return registrationStepProvider.registrationSteps.map { it.stepName }
    }
}