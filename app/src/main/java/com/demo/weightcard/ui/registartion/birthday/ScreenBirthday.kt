package com.demo.weightcard.ui.registartion.birthday

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenBirthdayBinding
import com.demo.weightcard.ui.registartion.RegistrationNavigator
import com.demo.weightcard.utils.registrationViewModelDelegate

class ScreenBirthday : Fragment(R.layout.screen_birthday) {
    private val binding by viewBinding(ScreenBirthdayBinding::bind)
    private val viewModel by registrationViewModelDelegate<ScreenBirthdayViewModel>()
    private val registrationNavigator = RegistrationNavigator()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        bindListeners()
    }

    private fun bindListeners() {
        bindEditTextToViewModel(viewModel.weight, binding.birthday)
        binding.nextScreen.setOnClickListener {
            if (viewModel.isAllFieldsValid())
                registrationNavigator.moveToNextScreen(parentFragment)
        }
        binding.prevScreen.setOnClickListener {
            registrationNavigator.moveToPreviousScreen(parentFragment)
        }
    }

    private fun bindEditTextToViewModel(liveData: MutableLiveData<String>?, editText: EditText) {
        liveData?.observe(viewLifecycleOwner) {
            if (it != editText.text.toString()) {
                editText.setText(it)
            }
        }
        editText.addTextChangedListener {
            liveData?.value = it?.toString()
        }
    }
}