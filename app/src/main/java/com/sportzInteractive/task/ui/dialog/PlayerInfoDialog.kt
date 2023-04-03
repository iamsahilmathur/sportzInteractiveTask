package com.sportzInteractive.task.ui.dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import com.sportzInteractive.task.databinding.PlayersInfoDialogBinding
import com.sportzInteractive.task.model.response.Players
class PlayerInfoDialog(context: Context,val  playersData: Players) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = PlayersInfoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        with(binding){
            //setPlayerData
            textViewPlayerName.text="${playersData.Name_Full} ${getPlayerType(playersData.Iscaptain?:false,playersData.Iskeeper?:false)}"
            //set batting Data
            textViewBattingStyle.text="Style:- ${playersData.Batting?.Style?:"N.A"}"
            textViewRuns.text="Runs:- ${playersData.Batting?.Runs?:"N.A"}"
            textViewBattingAverage.text="Average:- ${playersData.Batting?.Average?:"N.A"}"
            textViewStrikeRate.text="Strike Rate:- ${playersData.Batting?.Strikerate?:"N.A"}"

            //set Bowling Data
            textViewBowlingStyle.text="End:- ${playersData.Bowling?.Style?:"N.A"}"
            textViewEconomy.text="Economy:- ${playersData.Bowling?.Economyrate?:"N.A"}"
            textViewBowlingAvrg.text="Average:- ${playersData.Bowling?.Average?:"N.A"}"
            textViewWickets.text="Wickets:- ${playersData.Bowling?.Wickets?:"N.A"}"
        }
        binding.imageViewClose.setOnClickListener {
            dismiss()
        }
    }

    private fun getPlayerType(isCaptain: Boolean, isWicketKeeper: Boolean):String {
        return if(isCaptain&&isWicketKeeper){
            "(C & Wk)"
        }else if(isCaptain){
            "(C)"
        }else if(isWicketKeeper){
            "(Wk)"
        }else{
            ""
        }
    }
}

fun Fragment.showPlayerInfo(playersData: Players): Lazy<PlayerInfoDialog> = lazy {
    PlayerInfoDialog(requireContext(),playersData)
}
