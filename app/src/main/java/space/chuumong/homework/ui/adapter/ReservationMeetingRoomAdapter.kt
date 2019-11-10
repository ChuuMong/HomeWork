package space.chuumong.homework.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.homework.R

class ReservationMeetingRoomAdapter :
    RecyclerView.Adapter<ReservationMeetingRoomAdapter.ReservationMeetingRoomViewHolder>() {

    private val rooms = mutableListOf<MeetingRoomInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationMeetingRoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reservation_meeting_room, parent, false)
        return ReservationMeetingRoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holder: ReservationMeetingRoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): MeetingRoomInfo {
        return rooms[position]
    }

    fun addAll(items: List<MeetingRoomInfo>) {
        rooms.clear()
        rooms.addAll(items)
        notifyDataSetChanged()
    }

    inner class ReservationMeetingRoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvRoomName = itemView as TextView

        fun bind(item: MeetingRoomInfo) {
            tvRoomName.text = item.name
        }

    }
}