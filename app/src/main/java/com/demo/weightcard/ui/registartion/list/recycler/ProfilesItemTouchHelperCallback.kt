package com.demo.weightcard.ui.registartion.list.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ProfilesItemTouchHelperCallback(private val helperAdapter: HelperAdapter) :
    ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.START
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        helperAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        helperAdapter.onItemDismiss(viewHolder.adapterPosition)
    }
}