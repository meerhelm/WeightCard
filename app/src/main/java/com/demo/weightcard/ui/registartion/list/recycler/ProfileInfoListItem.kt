package com.demo.weightcard.ui.registartion.list.recycler

data class ProfileInfoListItem(
    val id: Long,
    val weight: String = "",
    val birthday: String = "",
    val selectionModeEnabled: Boolean,
    val itemSelected: Boolean,
    val photo: String = "",
    val units:String = ""
)