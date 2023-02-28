package com.sportzInteractive.task.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sportzInteractive.task.R
import com.sportzInteractive.task.model.response.Players
import kotlinx.android.synthetic.main.row_match_list.view.*
import kotlinx.android.synthetic.main.row_player_list.view.*

class PlayerAdapter(
    val context: Context,
    var playersList: ArrayList<Players>,
    var listener: OnItemClickedListener
) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_player_list, parent, false)
        return ViewHolder(view)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(items: Players) {
            itemView.playerName.text = "${items.Name_Full}"
            val playerInfo = getPlayerInfo(
                items.Iscaptain ?: false,
                items.Iskeeper ?: false
            )
            if (playerInfo.isEmpty()) {
                itemView.textViewPlayerInfo.text = items.Batting?.Style
            } else {
                itemView.textViewPlayerInfo.text = playerInfo + "-" + items.Batting?.Style
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