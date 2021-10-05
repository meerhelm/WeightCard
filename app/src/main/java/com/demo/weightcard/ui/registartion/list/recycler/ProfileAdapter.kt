package com.demo.weightcard.ui.registartion.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.weightcard.R

class ProfileAdapter(
    private var items: List<ProfileInfoListItem>,
    private val onItemSelected: (profile: ProfileInfoListItem) -> Unit,
    private val onProfileLongClick: (profile: ProfileInfoListItem) -> Unit,
    private val onProfileDismissed: (profile: ProfileInfoListItem) -> Unit,
    private val onEditProfileClicked: (profile: ProfileInfoListItem) -> Unit
) : RecyclerView.Adapter<ProfileViewHolder>(), HelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(
            view,
            onItemSelected,
            onProfileLongClick,
            onProfileDismissed,
            onEditProfileClicked
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindProfile(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(items: List<ProfileInfoListItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemDismiss(position: Int) {
        onProfileDismissed.invoke(items[position])
    }

    override fun onActionEnded() {
    }
}