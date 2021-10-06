package com.demo.weightcard.ui.registartion.list.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.demo.weightcard.R

class ProfileViewHolder(
    private val view: View,
    private val onItemSelected: (profile: ProfileInfoListItem) -> Unit,
    private val onDeleteItemClicked: (profile: ProfileInfoListItem) -> Unit,
    private val onEditItemClicked: (profile: ProfileInfoListItem) -> Unit
) :
    RecyclerView.ViewHolder(view) {
    //    private val photo = view.findViewById<ImageView>(R.id.photo)
    private val id = view.findViewById<TextView>(R.id.id)
    private val fullName = view.findViewById<TextView>(R.id.fullName)
    private val profileItemCheckbox =
        view.findViewById<AppCompatCheckBox>(R.id.profile_item_checkbox)
    private val editButton = view.findViewById<ImageView>(R.id.edit)

    fun bindProfile(profile: ProfileInfoListItem) {
        id.text = profile.id.toString()
        fullName.text = "${profile.weight} ${profile.birthday}"

        profileItemCheckbox.isChecked = profile.itemSelected
        profileItemCheckbox.setOnClickListener {
            onItemSelected.invoke(profile)
        }
        editButton.setOnClickListener {
            onEditItemClicked.invoke(profile)
        }
    }
}