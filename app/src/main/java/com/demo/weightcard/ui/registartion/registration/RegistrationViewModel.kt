package com.demo.weightcard.ui.registartion.registration

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.weightcard.logic.interactor.RegistrationInteractor
import com.demo.weightcard.logic.registration_flow.RegistrationStep
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.model.Profile
import com.demo.weightcard.ui.registartion.RegistrationNavigator
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationInteractor: RegistrationInteractor
) : ViewModel() {
    val registrationNavigationEnabled = MutableLiveData<Boolean>()

    val registrationSteps = MutableLiveData<List<RegistrationStep>>()
    var viewModelsDelegates = emptyList<RegistrationViewModelDelegate>()
    val isLoadingVisible = MutableLiveData(false)

    val id = MutableLiveData<Long>()

    private var currentRegistrationStep = 0
    private val validationObserver = Observer<Any> { checkAllStepFieldsFilled() }

    //TODO load profile here
    fun loadRegistrationFlow(profileId: Long?) = viewModelScope.launch {
        val profile = registrationInteractor.getRegistrationProfile(profileId)
        isLoadingVisible.value = false
        id.value = profile.id

        val steps = profile.registrationFlow
        registrationSteps.value = steps
        viewModelsDelegates = steps.map {
            it.getViewModelDelegate(profile)
        }
    }

    inline fun <reified T : RegistrationViewModelDelegate> getViewModelDelegate(): T? {
        return viewModelsDelegates.find { it is T } as T?
    }

    fun saveProfileAndClose(
        registrationNavigator: RegistrationNavigator,
        parentFragment: Fragment?,
        weight: String?,
        birthday: String?,
        photo: Bitmap?,
    ) = viewModelScope.launch {
        isLoadingVisible.value = true
        var intFingerId = 0


        val profile = Profile(
            id.value!!,
            weight,
            birthday,
            photo
        )
        registrationInteractor.writeProfile(profile)
        isLoadingVisible.value = false
        registrationNavigator.moveToNextScreen(parentFragment)
    }

    fun onViewPagerStepChanged(stepIndex: Int) {
        //unlisten old step changes
        viewModelsDelegates[currentRegistrationStep].getAllLiveData().forEach {
            it.removeObserver(validationObserver)
        }
        currentRegistrationStep = stepIndex
        checkAllStepFieldsFilled()

        //Listen all step livedatas to validate step on any step changes
        viewModelsDelegates[currentRegistrationStep].getAllLiveData().forEach {
            it.observeForever(validationObserver)
        }
    }

    private fun checkAllStepFieldsFilled() {
        registrationNavigationEnabled.value =
            viewModelsDelegates[currentRegistrationStep].isAllFieldsValid()
    }
}