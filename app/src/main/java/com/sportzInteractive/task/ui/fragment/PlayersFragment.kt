package com.sportzInteractive.task.ui.fragment
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sportzInteractive.task.BR
import com.sportzInteractive.task.R
import com.sportzInteractive.task.databinding.FragmentPlayersBinding
import com.sportzInteractive.task.model.response.MatchInfoData
import com.sportzInteractive.task.model.response.Players
import com.sportzInteractive.task.ui.adapter.PlayerAdapter
import com.sportzInteractive.task.ui.base.BaseFragment
import com.sportzInteractive.task.ui.dialog.showPlayerInfo
import com.sportzInteractive.task.ui.viewModel.MatchInfoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayersFragment : BaseFragment<FragmentPlayersBinding, MatchInfoViewModel>(),
    PlayerAdapter.OnItemClickedListener {
    private val args: PlayersFragmentArgs by navArgs()
    private val mViewModel: MatchInfoViewModel by viewModels()
    private lateinit var matchInfoModel: MatchInfoData
    private var playerAdapter: PlayerAdapter? = null
    private var playerAdapterTwo: PlayerAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBinding().homeVM = mViewModel
        setupViews()
        setData()
        onCheckedChangeListener()
    }

    override fun getLayoutId() = R.layout.fragment_players

    override fun getViewModel() = mViewModel

    override fun getBindingVariable() = BR.homeVM

    override fun observeViewModel() {

    }

    private fun setupViews() {

        playerAdapter = PlayerAdapter(requireContext(), this)
        playerAdapterTwo = PlayerAdapter(requireContext(), this)

        getBinding().recyclerViewTeamOne.apply {
            adapter = playerAdapter
        }

        getBinding().recyclerViewTeamTwo.apply {
            adapter = playerAdapterTwo
        }
        matchInfoModel = args.matchInfoData
    }

    private fun setData() {
        if (::matchInfoModel.isInitialized) {
            playerAdapter?.setPlayerList(matchInfoModel.Teams[0].Players)
            getBinding().radioButtonTeamOne.text = matchInfoModel.Teams[0].Name_Full
            getBinding().radioButtonTeamTwo.text = matchInfoModel.Teams[1].Name_Full
            playerAdapterTwo?.setPlayerList(matchInfoModel.Teams[1].Players)
        }
    }

    private fun onCheckedChangeListener() {
        getBinding().radioGroupTeams.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radioButtonTeamOne -> {
                    playerAdapter?.setPlayerList(matchInfoModel.Teams[0].Players)
                    getBinding().recyclerViewTeamTwo.visibility = View.GONE
                    getBinding().recyclerViewTeamOne.visibility = View.VISIBLE
                }
                R.id.radioButtonTeamTwo -> {
                    playerAdapter?.setPlayerList(matchInfoModel.Teams[1].Players)
                    getBinding().recyclerViewTeamTwo.visibility = View.VISIBLE
                    getBinding().recyclerViewTeamOne.visibility = View.GONE
                }
                R.id.radioButtonTeamBoth -> {
                    getBinding().recyclerViewTeamTwo.visibility = View.VISIBLE
                    getBinding().recyclerViewTeamOne.visibility = View.VISIBLE
                    playerAdapter?.setPlayerList(matchInfoModel.Teams[0].Players)
                    playerAdapterTwo?.setPlayerList(matchInfoModel.Teams[1].Players)
                }
            }
        }
    }

    override fun itemClicked(players: Players) {
        val playerInfoDialog by showPlayerInfo(players)
        playerInfoDialog.show()
    }
}