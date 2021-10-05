package com.demo.weightcard.logic.interactor

import com.demo.weightcard.model.Profile
import com.demo.weightcard.data.repository.ProfileRepository
import com.demo.weightcard.data.repository.RegistrationRepository
import kotlin.random.Random

class RegistrationInteractor(
    private val profileRepository: ProfileRepository
) {
    suspend fun getRegistrationProfile(profileId: Long?): Profile {
        if (profileId != null) {
            return profileRepository.getProfile(profileId)!!
        }
        return Profile(
            id = Random.nextLong(100000000000, 1000000000000),
        )
    }

    suspend fun writeProfile(profile: Profile) {
        val existingProfile = profileRepository.getProfile(profile.id)
        if (existingProfile != null) {
            profileRepository.removeProfile(profile)
        }
        return profileRepository.writeProfile(profile)
    }
}