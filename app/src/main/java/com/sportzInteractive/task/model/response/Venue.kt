package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Venue(
    val Id: String,
    val Name: String
): Parcelable