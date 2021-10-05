package com.demo.weightcard.utils

import androidx.fragment.app.Fragment
import com.demo.weightcard.logic.registration_flow.RegistrationViewModelDelegate
import com.demo.weightcard.ui.registartion.registration.RegistrationViewModel
import org.koin.android.ext.android.getDefaultScope
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

inline fun <reified T : RegistrationViewModelDelegate> Fragment.registrationViewModelDelegate(): Lazy<T> {
    return lazy {
        val registrationViewModel = getSharedViewModel<RegistrationViewModel>()
        val viewModelDelegate = registrationViewModel.getViewModelDelegate<T>()
        viewModelDelegate ?: getDefaultScope().inject<T>().value
    }
}