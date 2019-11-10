package space.chuumong.homework.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.homework.ui.adapter.ReservationMeetingRoomAdapter

@BindingAdapter("attachReservationMeetingRooms")
fun attachReservationMeetingRooms(rv: RecyclerView, reservationRooms: List<MeetingRoomInfo>?) {
    if (reservationRooms == null) {
        return
    }

    val adapter = ReservationMeetingRoomAdapter()
    rv.adapter = adapter
    adapter.addAll(reservationRooms)
}