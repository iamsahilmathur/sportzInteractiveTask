package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Players(
val Position: String?,
val Name_Full: String?,
val Iskeeper: Boolean?,
val Iscaptain: Boolean?,
val Batting: Batting?,
val Bowling: Bowling?,
): Parcelable