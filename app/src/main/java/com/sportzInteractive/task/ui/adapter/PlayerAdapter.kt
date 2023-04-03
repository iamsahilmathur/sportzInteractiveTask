package com.sportzInteractive.task.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sportzInteractive.task.R
import com.sportzInteractive.task.databinding.RowMatchListBinding
import com.sportzInteractive.task.databinding.RowPlayerListBinding
import com.sportzInteractive.task.model.response.Players
import kotlinx.android.synthetic.main.row_player_list.view.*

class PlayerAdapter(
    val context: Context,
    var listener:OnItemClickedListener
) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    var playersList=ArrayList<Players>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<RowPlayerListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_player_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playersList[position])
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    fun updateData(it: ArrayList<Players>?) {
        playersList.clear()
        it?.let { it1 -> playersList.addAll(it1) }
        notifyDataSetChanged()
    }

    fun getPlayerInfo(isKeeper: Boolean, isCap: Boolean): String {
        return if (isKeeper && isCap) {
            "(C & WC)"
        } else if (isCap) {
            "(C)"
        } else if (isKeeper) {
            "(WC)"
        } else {
            ""
        }
    }

    fun setPlayerList(players: java.util.ArrayList<Players>) {
        playersList.clear()
        playersList.addAll(players)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val itemBinding: RowPlayerListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: Players) {
            itemBinding.playerName.text = "${items.Name_Full}"
            val playerInfo = getPlayerInfo(
                items.Iscaptain ?: false,
                items.Iskeeper ?: false
            )
            if (playerInfo.isEmpty()) {
                itemBinding.textViewPlayerInfo.text = items.Batting?.Style
            } else {
                itemBinding.textViewPlayerInfo.text = playerInfo + "-" + items.Batting?.Style
            }
            itemView.setOnClickListener {
                listener.itemClicked(items)
            }

        }
    }

    interface OnItemClickedListener {
        fun itemClicked(players: Players)
    }
}