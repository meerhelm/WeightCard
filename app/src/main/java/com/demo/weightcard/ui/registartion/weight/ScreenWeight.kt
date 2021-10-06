package com.demo.weightcard.ui.registartion.weight

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenWeightBinding
import com.demo.weightcard.ui.registartion.RegistrationNavigator
import com.demo.weightcard.utils.bindEditTextToViewModel
import com.demo.weightcard.utils.registrationViewModelDelegate

class ScreenWeight : Fragment(R.layout.screen_weight) {
    private val binding by viewBinding(ScreenWeightBinding::bind)
    private val viewModel by registrationViewModelDelegate<ScreenWeightViewModel>()
    private val registrationNavigator = RegistrationNavigator()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        bindListeners()
    }

    private fun bindListeners() {
        bindEditTextToViewModel(viewModel.weight, binding.weight)
        binding.nextScreen.setOnClickListener {
            if (viewModel.isAllFieldsValid())
                registrationNavigator.moveToNextScreen(parentFragment)
        }
        binding.prevScreen.setOnClickListener {
            registrationNavigator.moveToPreviousScreen(parentFragment)
        }
    }

}

