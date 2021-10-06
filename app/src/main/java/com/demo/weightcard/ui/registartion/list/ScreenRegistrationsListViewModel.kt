package com.demo.weightcard.ui.registartion.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.demo.weightcard.data.repository.ProfileRepository
import com.demo.weightcard.model.Profile
import com.demo.weightcard.ui.registartion.list.recycler.ProfileInfoListItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class ScreenRegistrationsListViewModel(private val profileRepository: ProfileRepository) :
    ViewModel() {

    val profiles = MutableLiveData<List<ProfileInfoListItem>>()

    private val searchQueryFlowMutable = MutableSharedFlow<String>()

    fun loadProfiles() = viewModelScope.launch {
        profiles.value = profileRepository.getProfiles()
            .map { mapProfileToListItem(it) }
    }

    private fun listenSearchQuery() = viewModelScope.launch {
        searchQueryFlowMutable.debounce(500).collect {
            profiles.value =
                profileRepository.filterProfiles(it)
                    .map { profileInfo -> mapProfileToListItem(profileInfo) }
        }
    }

    private fun mapProfileToListItem(profileInfo: Profile) = ProfileInfoListItem(
        weight = profileInfo.weight,
        id = profileInfo.id,
        birthday = profileInfo.birthday,
        selectionModeEnabled = false,
        itemSelected = false,
        photo = profileInfo.face,
        units = profileInfo.units
    )


    fun deleteItem(profile: ProfileInfoListItem) = viewModelScope.launch {
        profileRepository.removeProfilesById(profile.id)
        loadProfiles()
    }

    fun updateSearchQuery(newText: String) = viewModelScope.launch {
        if (searchQueryFlowMutable.subscriptionCount.value == 0) {
            listenSearchQuery()
        }
        searchQueryFlowMutable.emit(newText)
    }
}