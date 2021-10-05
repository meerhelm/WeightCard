package com.demo.weightcard.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    val byteArray: ByteArray = stream.toByteArray()
//    recycle()
    return byteArray
}