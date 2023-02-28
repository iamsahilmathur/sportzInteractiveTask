package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val Code: String,
    val Date: String,
    val Daynight: String,
    val Id: String,
    val League: String,
    val Livecoverage: String,
    val Number: String,
    val Offset: String,
    val Time: String,
    val Type: String
): Parcelable