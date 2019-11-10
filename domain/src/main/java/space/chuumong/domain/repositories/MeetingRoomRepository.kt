package space.chuumong.domain.repositories

import io.reactivex.Single
import space.chuumong.domain.entities.MeetingRoomInfo

interface MeetingRoomRepository {

    fun getMeetingRoomInfo(): Single<List<MeetingRoomInfo>>
}