package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Batting(
    val Average: String,
    val Runs: String,
    val Strikerate: String,
    val Style: String
): Parcelable