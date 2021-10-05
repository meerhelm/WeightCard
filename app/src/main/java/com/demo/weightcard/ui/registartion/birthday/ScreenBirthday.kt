package com.demo.weightcard.ui.registartion.birthday

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.weightcard.R

class ScreenBirthday : Fragment() {

    companion object {
        fun newInstance() = ScreenBirthday()
    }

    private lateinit var viewModel: ScreenBirthdayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_birthday_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScreenBirthdayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}