package com.coonfbeer.ui.adapter

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coonfbeer.R
import com.coonfbeer.model.Conference

class ScheduleAdapter(val scheduleAdapter: ScheduleAdapter) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()

    override  fun getItemCount() = listConference.size

    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conference = listConference[position]
        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.dateTime
        val hourFormat = simpleDateFormat.format(conference.dateTime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.dateTime).toUpperCase()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleTituloConferencia)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemSchedulerHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemSchedulerAMPM)

    }
}