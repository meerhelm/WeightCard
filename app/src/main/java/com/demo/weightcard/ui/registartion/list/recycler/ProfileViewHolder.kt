package com.demo.weightcard.ui.registartion.list.recycler

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.weightcard.R

class ProfileViewHolder(
    private val view: View,
    private val onEditItemClicked: (profile: ProfileInfoListItem) -> Unit
) :
    RecyclerView.ViewHolder(view) {
    private val photo = view.findViewById<ImageView>(R.id.photo)
    private val weight = view.findViewById<TextView>(R.id.id)
    private val birthday = view.findViewById<TextView>(R.id.fullName)
    private val editButton = view.findViewById<ImageView>(R.id.edit)

    fun bindProfile(profile: ProfileInfoListItem) {
        weight.text = profile.weight.toString()
        birthday.text = profile.birthday.toString()
        photo.setImageURI(Uri.parse(profile.photo))
        editButton.setOnClickListener {
            onEditItemClicked.invoke(profile)
        }
    }
}