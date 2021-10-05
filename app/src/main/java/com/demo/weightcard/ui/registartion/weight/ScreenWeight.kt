package com.demo.weightcard.ui.registartion.weight

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.weightcard.R

class ScreenWeight : Fragment() {

    companion object {
        fun newInstance() = ScreenWeight()
    }

    private lateinit var viewModel: ScreenWeightViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_weight, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScreenWeightViewModel::class.java)
        // TODO: Use the ViewModel
    }

}