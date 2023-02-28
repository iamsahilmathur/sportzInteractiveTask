package com.sportzInteractive.task.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MatchInfoData(
    val Matchdetail: Matchdetail,
    var Teams: ArrayList<TeamsData>
): Parcelable

@Parcelize
data class TeamsData(
    var Name_Full: String,
    val Name_Short: String,
    val Players: ArrayList<Players>
):Parcelable

