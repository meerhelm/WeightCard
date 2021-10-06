package com.demo.weightcard.ui.registartion

import androidx.fragment.app.Fragment
import com.demo.weightcard.ui.registartion.registration.ScreenRegistration

class RegistrationNavigator {
    fun moveToNextScreen(parentFragment: Fragment?) {
        (parentFragment as? ScreenRegistration)?.moveToNextScreen()
    }

    fun moveToPreviousScreen(parentFragment: Fragment?) {
        (parentFragment as? ScreenRegistration)?.moveToPreviousScreen()
    }

}