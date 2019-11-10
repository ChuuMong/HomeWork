package space.chuumong.homework.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.homework.R
import space.chuumong.homework.ui.view.MeetingRoomTimeView

class MeetingRoomInfoAdapter :
    RecyclerView.Adapter<MeetingRoomInfoAdapter.MeetingRoomInfoViewHolder>() {

    private val infos = mutableListOf<MeetingRoomInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingRoomInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meeting_room_info, parent, false)
        return MeetingRoomInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return infos.size
    }

    override fun onBindViewHolder(holder: MeetingRoomInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): MeetingRoomInfo {
        return infos[position]
    }

    fun addAll(items: List<MeetingRoomInfo>) {
        infos.clear()
        infos.addAll(items)
        notifyDataSetChanged()
    }

    inner class MeetingRoomInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvRoomName = view.findViewById<TextView>(R.id.tv_room_name)
        private val tvRoomPosition = view.findViewById<TextView>(R.id.tv_room_position)

        private val meetingRoomTimeView =
            view.findViewById<MeetingRoomTimeView>(R.id.view_meeting_room_time)

        fun bind(item: MeetingRoomInfo) {
            tvRoomName.text = item.name
            tvRoomPosition.text = item.location

            meetingRoomTimeView.setMeetingRoomInfo(item)
        }

    }
}