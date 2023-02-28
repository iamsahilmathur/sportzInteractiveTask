package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Officials(
    val Referee: String,
    val Umpires: String
): Parcelable