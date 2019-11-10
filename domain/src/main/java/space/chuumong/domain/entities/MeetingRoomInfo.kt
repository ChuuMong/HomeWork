package space.chuumong.domain.entities

data class MeetingRoomInfo(
    val name: String,
    val location: String,
    val reservations: List<MeetingRoomReservation>
)

data class MeetingRoomReservation(
    val startTime: String,
    val endTime: String
)