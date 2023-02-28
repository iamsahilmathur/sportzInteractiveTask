package com.sportzInteractive.task.ui.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sportzInteractive.task.R
import com.sportzInteractive.task.model.response.MatchInfoData
import com.sportzInteractive.task.utils.DateFormatUtils
import kotlinx.android.synthetic.main.row_match_list.view.*

class MatchInfoAdapter(
    val context: Context,
    var matchList: ArrayList<MatchInfoData>,
    var listener: OnItemClickedListener
) :
    RecyclerView.Adapter<MatchInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_match_list, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matchList[position]!!)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    fun updateData(it: ArrayList<MatchInfoData>?) {
        matchList.clear()
        it?.let { it1 -> matchList.addAll(it1) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(items: MatchInfoData) {
            itemView.textViewDateTime.text =
                DateFormatUtils.getDisplayDate(items.Matchdetail.Match.Date)
            itemView.teamName.text = items.Teams[0].Name_Full + " vs " + items.Teams[1].Name_Full
            val venue = "<b>Venue: </b> ${items.Matchdetail.Venue.Name}"
            itemView.textViewVenue.text = Html.fromHtml(venue)
            val time = "<b>Time: </b> ${items.Matchdetail.Match.Time}"
            itemView.textViewTime.text = Html.fromHtml(time)
            itemView.textViewNumber.text = items.Matchdetail.Match.Number+", "+items.Matchdetail.Series.Name
            itemView.setOnClickListener {
                listener.itemClicked(items)
            }
            itemView.textViewInfoMatch.setOnClickListener {
                listener.itemClicked(items)
            }
        }
    }

    interface OnItemClickedListener {
        fun itemClicked(matchInfoData: MatchInfoData)
    }
}