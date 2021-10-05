package com.demo.weightcard.ui.registartion.list.recycler

interface HelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)

    fun onActionEnded()
}