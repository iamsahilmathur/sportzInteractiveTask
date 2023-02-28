package com.sportzInteractive.task.ui.activity.home
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sportzInteractive.task.R
import com.sportzInteractive.task.extentions.manageLoading
import com.sportzInteractive.task.model.response.MatchInfoData
import com.sportzInteractive.task.ui.adapter.MatchInfoAdapter
import com.sportzInteractive.task.ui.base.BaseActivity
import com.sportzInteractive.task.utils.Constant.Companion.MATCH_INFO
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(){
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var matchInfoAdapter: MatchInfoAdapter
    override fun findFragmentPlaceHolder() = 0
    override fun findContentView() = R.layout.activity_home
    override fun observeViewModel() {
        setAdapter()

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.manageLoading(
            this, this, homeViewModel.delegateMatchInfo,
            homeViewModel.errorLiveData
        ).getMatchInfo()

        homeViewModel.delegateMatchInfo.observe(this) {
            matchInfoAdapter.updateData(it as ArrayList<MatchInfoData>?)
        }
    }

    private fun setAdapter() {
        matchInfoAdapter = MatchInfoAdapter(this, ArrayList(), object :
            MatchInfoAdapter.OnItemClickedListener {
            override fun itemClicked(matchInfoData: MatchInfoData) {
                val bundle=Bundle()
                bundle.putParcelable(MATCH_INFO,matchInfoData)
                PlayerListActivity.start(this@HomeActivity,bundle)
            }
        })
        recyclerViewMatchList.apply {
            adapter = matchInfoAdapter
        }
    }
}