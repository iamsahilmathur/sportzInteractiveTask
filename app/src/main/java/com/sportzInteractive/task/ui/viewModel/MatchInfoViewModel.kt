package com.sportzInteractive.task.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sportzInteractive.task.extentions.manageNetwork
import com.sportzInteractive.task.lifecycle.ErrorModelView
import com.sportzInteractive.task.lifecycle.SingleLiveData
import com.sportzInteractive.task.model.response.*
import com.sportzInteractive.task.repository.Repository
import com.sportzInteractive.task.utils.Constant.Companion.MATCH_INFO_1
import com.sportzInteractive.task.utils.Constant.Companion.MATCH_INFO_2
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext val context: Context,
) : ViewModel() {
    var delegateMatchInfo = SingleLiveData<List<MatchInfoData>>()
    var matchInfoList = ArrayList<MatchInfoData>()
    val errorLiveData: SingleLiveData<ErrorModelView> = SingleLiveData()
    var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorLiveData.postValue(
            ErrorModelView(
                messageTitle = throwable.message,
                tagException = throwable.printStackTrace().toString()
            )
        )
    }

    fun getMatchInfo() {
        viewModelScope.manageNetwork(context, exceptionHandler)
            ?.launch(exceptionHandler) {
                withContext(Dispatchers.IO) {
                    repository.getMatchInfo(MATCH_INFO_1).apply {
                        matchInfoList.clear()
                        matchInfoList.add(filterDataInfo(this))
                        getMatchInfoOther()
                    }
                }
            }
    }

    private fun getMatchInfoOther() {
        viewModelScope.manageNetwork(context, exceptionHandler)
            ?.launch(exceptionHandler) {
                withContext(Dispatchers.IO) {
                    repository.getMatchInfo(MATCH_INFO_2).apply {
                        matchInfoList.add(filterDataInfo(this))
                        delegateMatchInfo.postValue(matchInfoList)
                    }
                }
            }
    }

    private fun filterDataInfo(matchDetailData: MatchInfoResponse): MatchInfoData {
        val teamsDataList = ArrayList<TeamsData>()
        matchDetailData.Teams.forEach { (keys, values) ->
            val playerList = ArrayList<Players>()
            values.Players?.forEach { (key, value) ->
                playerList.add(value)
            }
            teamsDataList.add(TeamsData(values.Name_Full!!, values.Name_Short!!, playerList))
        }
        return MatchInfoData(matchDetailData.Matchdetail, teamsDataList)
    }
}