package space.chuumong.domain.repositories

import io.reactivex.Single
import space.chuumong.domain.entities.MeetingRoomInfo


/**
 * Created by Home on 2019-11-10.
 */
interface MeetingRoomRepository {

    fun getMeetingRoomInfo(): Single<List<MeetingRoomInfo>>
}