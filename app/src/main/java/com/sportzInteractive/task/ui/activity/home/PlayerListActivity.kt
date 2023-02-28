package com.sportzInteractive.task.ui.activity.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sportzInteractive.task.R
import com.sportzInteractive.task.model.response.MatchInfoData
import com.sportzInteractive.task.model.response.Players
import com.sportzInteractive.task.ui.adapter.MatchInfoAdapter
import com.sportzInteractive.task.ui.adapter.PlayerAdapter
import com.sportzInteractive.task.ui.base.BaseActivity
import com.sportzInteractive.task.ui.dialog.PlayersInfoDialog
import com.sportzInteractive.task.utils.Constant.Companion.MATCH_INFO
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_player_list.*

class PlayerListActivity : BaseActivity() {
    var matchInfoData: MatchInfoData? = null
    lateinit var playerAdapter: PlayerAdapter
    lateinit var playerAdapterTemTwo: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun findFragmentPlaceHolder() = 0
    override fun findContentView() = R.layout.activity_player_list
    override fun observeViewModel() {
        setAdapter()
        if (intent.extras?.containsKey(MATCH_INFO)!!) {
            matchInfoData = intent.extras?.getParcelable(MATCH_INFO)!!
            radioButtonTeamOne.text = matchInfoData?.Teams?.get(0)?.Name_Full
            radioButtonTeamTwo.text = matchInfoData?.Teams?.get(1)?.Name_Full
            onCheckedChangeListener()
            playerAdapter.updateData(matchInfoData?.Teams?.get(0)?.Players)
            playerAdapterTemTwo.updateData(matchInfoData?.Teams?.get(1)?.Players)
        }

        imageViewBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun start(context: BaseActivity, bundle: Bundle) {
            val intent = Intent(context, PlayerListActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    private fun onCheckedChangeListener() {
        radioGroupTeams.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radioButtonTeamOne -> {
                    playerAdapter.updateData(matchInfoData?.Teams?.get(0)?.Players)
                    recyclerViewTeamTwo.visibility = View.GONE
                    recyclerViewTeamOne.visibility = View.VISIBLE
                }
                R.id.radioButtonTeamTwo -> {
                    playerAdapter.updateData(matchInfoData?.Teams?.get(1)?.Players)
                    recyclerViewTeamTwo.visibility = View.VISIBLE
                    recyclerViewTeamOne.visibility = View.GONE
                }
                R.id.radioButtonTeamBoth -> {
                    recyclerViewTeamTwo.visibility = View.VISIBLE
                    recyclerViewTeamOne.visibility = View.VISIBLE
                    playerAdapter.updateData(matchInfoData?.Teams?.get(0)?.Players)
                    playerAdapterTemTwo.updateData(matchInfoData?.Teams?.get(1)?.Players)
                }
            }
        }
    }

    private fun setAdapter() {
        playerAdapter = PlayerAdapter(this, ArrayList<Players>(), object :
            PlayerAdapter.OnItemClickedListener {
            override fun itemClicked(players: Players) {
                val dilog = PlayersInfoDialog()
                dilog.setPlayerInfo(players)
                dilog.setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog)
                dilog.show(supportFragmentManager, "players")
            }
        })
        recyclerViewTeamOne.apply {
            adapter = playerAdapter
        }

        playerAdapterTemTwo = PlayerAdapter(this, ArrayList<Players>(), object :
            PlayerAdapter.OnItemClickedListener {
            override fun itemClicked(players: Players) {
                val dilog = PlayersInfoDialog()
                dilog.setPlayerInfo(players)
                dilog.setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog)
                dilog.show(supportFragmentManager, "players")
            }
        })
        recyclerViewTeamTwo.apply {
            adapter = playerAdapterTemTwo
        }
    }
}