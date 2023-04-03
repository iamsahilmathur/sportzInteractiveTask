package com.sportzInteractive.task.ui.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sportzInteractive.task.R
import com.sportzInteractive.task.databinding.RowMatchListBinding
import com.sportzInteractive.task.model.response.MatchInfoData
import com.sportzInteractive.task.utils.DateFormatUtils

class MatchInfoAdapter(
    val context: Context,
    var listener: OnItemClickedListener
) :
    RecyclerView.Adapter<MatchInfoAdapter.ViewHolder>() {
    var matchList = ArrayList<MatchInfoData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<RowMatchListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_match_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    fun updateData(it: ArrayList<MatchInfoData>?) {
        matchList.clear()
        it?.let { it1 -> matchList.addAll(it1) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: RowMatchListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(items: MatchInfoData) {
            itemBinding.textViewDateTime.text =
                DateFormatUtils.getDisplayDate(items.Matchdetail.Match.Date)
            itemBinding.teamName.text = items.Teams[0].Name_Full + " vs " + items.Teams[1].Name_Full
            val venue = "<b>Venue: </b> ${items.Matchdetail.Venue.Name}"
            itemBinding.textViewVenue.text = Html.fromHtml(venue)
            val time = "<b>Time: </b> ${items.Matchdetail.Match.Time}"
            itemBinding.textViewTime.text = Html.fromHtml(time)
            itemBinding.textViewNumber.text =
                items.Matchdetail.Match.Number + ", " + items.Matchdetail.Series.Name
            itemView.setOnClickListener {
                listener.itemClicked(items)
            }
            itemBinding.textViewInfoMatch.setOnClickListener {
                listener.itemClicked(items)
            }
        }
    }

    interface OnItemClickedListener {
        fun itemClicked(matchInfoData: MatchInfoData)
    }
}