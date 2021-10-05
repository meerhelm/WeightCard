package com.demo.weightcard.model

import android.graphics.Bitmap

data class ProfileInfo(
    val id: Long,
    val weight: String?,
    val birthday: String?,
    val face: Bitmap?,
)