package com.sportzInteractive.task.repository
import com.sportzInteractive.task.network.ApiService
import javax.inject.Inject


class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMatchInfo(url:String)=apiService.getMatchInfo(url)
}