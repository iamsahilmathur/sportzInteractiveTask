package com.sportzInteractive.task.ui.dialog

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.sportzInteractive.task.R
import com.sportzInteractive.task.model.response.Players
import kotlinx.android.synthetic.main.players_info_dialog.*

class PlayersInfoDialog : DialogFragment(), View.OnClickListener {
    var players:Players?=null
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imageViewClose -> {
                dialog?.dismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.players_info_dialog, container, false)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.transparent)))
        dialog!!.setCancelable(true)
        return view
    }

    fun setPlayerInfo(players: Players)
    {
        this.players=players
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            imageViewClose.setOnClickListener(this)
            textViewPlayerName.text = players?.Name_Full
            textViewBattingStyle.text="Style:- ${players?.Batting?.Style?:""}"
            textViewRuns.text="Runs:- ${players?.Batting?.Runs?:""}"
            textViewBattingAverage.text="Average:- ${players?.Batting?.Average?:"N.A"}"
            textViewStrikeRate.text="Strike Rate:- ${players?.Batting?.Strikerate?:"N.A"}"

            textViewBowlingStyle.text="End:- ${players?.Bowling?.Style?:"N.A"}"
            textViewEconomy.text="Economy:- ${players?.Bowling?.Economyrate?:"N.A"}"
            textViewBowlingAvrg.text="Average:- ${players?.Bowling?.Average?:"N.A"}"
            textViewWickets.text="Wickets:- ${players?.Bowling?.Wickets?:"N.A"}"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
}
