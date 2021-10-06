package com.demo.weightcard.ui.registartion.birthday

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenBirthdayBinding
import com.demo.weightcard.ui.registartion.RegistrationNavigator
import com.demo.weightcard.utils.registrationViewModelDelegate
import java.util.*

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
        val calendar = Calendar.getInstance()
        binding.birthday.maxDate = calendar.timeInMillis
        if (viewModel.birthday.value != 0L) {
            calendar.timeInMillis = viewModel.birthday.value ?: 0
        }
        binding.birthday.init(
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ) { _, year, month, day ->
            calendar.set(year, month, day)
            viewModel.birthday.value = calendar.timeInMillis
        }
        viewModel.birthday.observe(viewLifecycleOwner) {
            val date = Calendar.getInstance()
            date.timeInMillis = it
            if (it != 0L &&
                date.get(Calendar.YEAR) != binding.birthday.year &&
                date.get(Calendar.MONTH) != binding.birthday.month &&
                date.get(Calendar.DAY_OF_MONTH) != binding.birthday.dayOfMonth
            )
                binding.birthday.updateDate(
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH)
                )
        }
        binding.nextScreen.setOnClickListener {
            if (viewModel.isAllFieldsValid())
                registrationNavigator.moveToNextScreen(parentFragment)
        }
        binding.prevScreen.setOnClickListener {
            registrationNavigator.moveToPreviousScreen(parentFragment)
        }
    }
}