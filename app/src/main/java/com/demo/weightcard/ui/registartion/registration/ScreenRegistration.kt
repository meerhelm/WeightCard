package com.demo.weightcard.ui.registartion.registration

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenRegistrationBinding
import com.demo.weightcard.logic.registration_flow.RegistrationStep
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ScreenRegistration : Fragment(R.layout.screen_registration) {
    private val viewModel by sharedViewModel<RegistrationViewModel>()
    private val binding by viewBinding(ScreenRegistrationBinding::bind)

    companion object {
        private const val PROFILE_ID_KEY = "profile_id"

        fun createBundle(profileId: Long) = Bundle().apply {
            putLong(PROFILE_ID_KEY, profileId)
        }
    }

    private var adapter: RecyclerView.Adapter<FragmentViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadRegistrationFlow(arguments?.getLong(PROFILE_ID_KEY))


        viewModel.registrationNavigationEnabled.observe(viewLifecycleOwner) {
            binding.viewPager.isUserInputEnabled = it
        }

        viewModel.registrationSteps.observe(viewLifecycleOwner) {
            bindViewPager(it)
        }

        viewModel.isLoadingVisible.observe(viewLifecycleOwner) {
            binding.loadingLayout.isVisible = it
        }
    }

    private fun bindViewPager(registrationSteps: List<RegistrationStep>) {
        adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = registrationSteps.size

            override fun createFragment(position: Int): Fragment =
                registrationSteps[position].getStepFragment()
        }
        binding.viewPager.isNestedScrollingEnabled = true
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onViewPagerStepChanged(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                viewModel.onViewPagerStepChanged(position)
            }
        })
    }

    fun moveToNextScreen() {
        if (adapter != null &&
            binding.viewPager.currentItem != adapter!!.itemCount - 1
        ) {
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        } else {
            findNavController().popBackStack()
        }
    }

    fun moveToPreviousScreen() {
        if (binding.viewPager.currentItem != 0) {
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
        } else {
            findNavController().popBackStack()
        }
    }

}