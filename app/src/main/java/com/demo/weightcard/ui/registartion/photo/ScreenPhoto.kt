package com.demo.weightcard.ui.registartion.photo

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenPhotoBinding
import com.demo.weightcard.ui.registartion.RegistrationNavigator
import com.demo.weightcard.utils.registrationViewModelDelegate

class ScreenPhoto : Fragment(R.layout.screen_photo) {
    private val binding by viewBinding(ScreenPhotoBinding::bind)
    private val viewModel by registrationViewModelDelegate<ScreenPhotoViewModel>()
    private val registrationNavigator = RegistrationNavigator()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        bindListeners()
    }

    private fun bindListeners() {
        binding.gallery.setOnClickListener { }
        binding.camera.setOnClickListener { }
        binding.nextScreen.setOnClickListener {
            if (viewModel.isAllFieldsValid())
                registrationNavigator.moveToNextScreen(parentFragment)
        }
        binding.prevScreen.setOnClickListener {
            registrationNavigator.moveToPreviousScreen(parentFragment)
        }
    }
}