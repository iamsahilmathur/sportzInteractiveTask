package com.sportzInteractive.task.model.response

data class MatchInfoResponse(
    val Innings: List<Inning>,
    val Matchdetail: Matchdetail,
    val Notes: Notes,
    val Nuggets: List<String>,
    var Teams:Map<String,TeamTestDataModelDTO>

)
data class TeamTestDataModelDTO(
    val Name_Full:String?,
    val Name_Short:String?,
    val Players:HashMap<String,Players>?,
)