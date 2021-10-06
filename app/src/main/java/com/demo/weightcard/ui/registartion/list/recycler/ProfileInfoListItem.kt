package com.demo.weightcard.ui.registartion.list.recycler

import android.graphics.Bitmap

data class ProfileInfoListItem(
    val id: Long,
    val weight: String?,
    val birthday: String?,
    val selectionModeEnabled: Boolean,
    val itemSelected: Boolean,
    val photo: String?,
)