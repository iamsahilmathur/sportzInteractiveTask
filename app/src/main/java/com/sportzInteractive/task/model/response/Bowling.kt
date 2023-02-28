package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bowling(
    val Average: String,
    val Economyrate: String,
    val Style: String,
    val Wickets: String
): Parcelable