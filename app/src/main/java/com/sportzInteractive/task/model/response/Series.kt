package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val Id: String,
    val Name: String,
    val Status: String,
    val Tour: String,
    val Tour_Name: String
):Parcelable