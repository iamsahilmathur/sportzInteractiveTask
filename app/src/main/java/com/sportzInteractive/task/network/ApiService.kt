package com.sportzInteractive.task.network
import com.sportzInteractive.task.model.response.MatchInfoResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getMatchInfo(@Url url:String): MatchInfoResponse
}