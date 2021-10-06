package com.demo.weightcard.ui.registartion.list.recycler

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.weightcard.R
import java.text.SimpleDateFormat

class ProfileViewHolder(
    view: View,
    private val onEditItemClicked: (profile: ProfileInfoListItem) -> Unit,
) :
    RecyclerView.ViewHolder(view) {
    private val photo = view.findViewById<ImageView>(R.id.photo)
    private val weight = view.findViewById<TextView>(R.id.weight)
    private val birthday = view.findViewById<TextView>(R.id.birthday)
    private val editButton = view.findViewById<ImageView>(R.id.edit)

    fun bindProfile(profile: ProfileInfoListItem) {
        val weightText = profile.weight + profile.units
        weight.text = weightText
        val formatter = SimpleDateFormat.getDateInstance()
        birthday.text = formatter.format(profile.birthday)
        photo.setImageURI(Uri.parse(profile.photo))
        editButton.setOnClickListener {
            onEditItemClicked.invoke(profile)
        }
    }
}