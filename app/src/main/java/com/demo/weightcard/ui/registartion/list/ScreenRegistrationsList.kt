package com.demo.weightcard.ui.registartion.list

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.demo.weightcard.R
import com.demo.weightcard.databinding.ScreenRegistrationsListBinding
import com.demo.weightcard.ui.registartion.list.recycler.ProfileAdapter
import com.demo.weightcard.ui.registartion.list.recycler.ProfileInfoListItem
import com.demo.weightcard.ui.registartion.list.recycler.ProfilesItemTouchHelperCallback
import com.demo.weightcard.ui.registartion.registration.ScreenRegistration
import org.koin.androidx.viewmodel.ext.android.viewModel


class ScreenRegistrationsList : Fragment(R.layout.screen_registrations_list) {
    private val binding by viewBinding(ScreenRegistrationsListBinding::bind)
    private val viewModel by viewModel<ScreenRegistrationsListViewModel>()
    private val adapter = ProfileAdapter(
        emptyList(),
        this::onItemDismissed,
        this::onEditItem
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profilesRecyclerView.adapter = adapter
        binding.profilesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.loadProfiles()
        viewModel.profiles.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
        initBottomBar()
        initSearchView()
        val callback = ProfilesItemTouchHelperCallback(adapter)
        ItemTouchHelper(callback).attachToRecyclerView(binding.profilesRecyclerView)
    }


    private fun onItemDismissed(profileInfo: ProfileInfoListItem) {
        viewModel.deleteItem(profileInfo)
    }

    private fun onEditItem(profileInfo: ProfileInfoListItem) {
        findNavController().navigate(
            R.id.screenRegistration,
            ScreenRegistration.createBundle(profileInfo.id)
        )
    }

    private fun initBottomBar() {
        binding.add.setOnClickListener {
            activity?.viewModelStore?.clear()
            findNavController().navigate(R.id.action_screenMain_to_screenRegistration)
        }
    }

    private fun initSearchView() {
        binding.searchEditText.doAfterTextChanged {
            viewModel.updateSearchQuery(it.toString())
        }
    }
}