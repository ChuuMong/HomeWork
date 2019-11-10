package space.chuumong.domain.usecase

import io.reactivex.Single
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.domain.repositories.MeetingRoomRepository

class GetMeetingRoomInfo(private val repository: MeetingRoomRepository) :
    UseCase<UseCase.None, List<MeetingRoomInfo>>() {

    override fun run(params: None): Single<List<MeetingRoomInfo>> {
        return repository.getMeetingRoomInfo()
    }
}