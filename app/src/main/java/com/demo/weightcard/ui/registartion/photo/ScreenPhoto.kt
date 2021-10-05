package com.demo.weightcard.ui.registartion.photo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.weightcard.R

class ScreenPhoto : Fragment() {

    companion object {
        fun newInstance() = ScreenPhoto()
    }

    private lateinit var viewModel: ScreenPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScreenPhotoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}